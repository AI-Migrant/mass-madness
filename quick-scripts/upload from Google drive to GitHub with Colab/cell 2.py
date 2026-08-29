!pip install splitzip
import os
import shutil
import splitzip
from google.colab import drive

# --- Parameters & Config --- #
USER_NAME = "AI-Migrant"
USER_EMAIL = "yourEmailAddress@.com"
REPO_DATE = "20260827"
REPO_NAME = f"persecuted-media-files-{REPO_DATE}"

DESTINATION_BASE_PATH = f"/content/{REPO_NAME}"
SIZE_THRESHOLD_MB = 50
SIZE_THRESHOLD_MIB = f"{SIZE_THRESHOLD_MB}MiB"
SIZE_THRESHOLD_BYTES = SIZE_THRESHOLD_MB * 1024 * 1024

GIT_PUSH_THRESHOLD_BYTES = 500 * 1024 * 1024  # Commit/Push trigger (500 MB)
SOURCE_FOLDERS = ["Folder 1", "Folder 2"]

# --- Git Setup & Initialization --- #
%cd "/content"
!ssh-keyscan -H github.com >> ~/.ssh/known_hosts
!git clone "git@github.com:{USER_NAME}/{REPO_NAME}.git"
%cd {DESTINATION_BASE_PATH}
!git config user.name {USER_NAME}
!git config user.email {USER_EMAIL}

# --- Mount Google Drive --- #
# print("Attempting to mount Google Drive...")
# try:
#  drive.mount('/content/drive', force_remount=True)
#  print("Google Drive mounted successfully.")
# except Exception as e:
#  print(f"Error mounting Google Drive: {e}")

readme_path = os.path.join(DESTINATION_BASE_PATH, "README.md")
if not os.path.exists(readme_path):
  shutil.copy2("/content/drive/My Drive/upload/Folder 1/README.md", readme_path)

# --- Helper Functions --- #
def git_commit_and_push(message="Sync media files batch"):
  """Stages all changes, commits, and pushes to remote."""
  print("\n[GIT] Reached processing threshold. Staging, committing, and pushing...")
  os.system("git add -A")
  os.system(f'git commit -m "{message}"')
  os.system("git push")
  print("[GIT] Push completed.\n")


def process_folder(source_path, pending_bytes=0):
  """
  Processes files in source_path: copies small files or zips large files.
  Triggers a git commit/push every time accumulated size exceeds GIT_PUSH_THRESHOLD_BYTES.
  Returns updated pending_bytes counter.
  """
  if not os.path.exists(source_path):
    print(f"Skipping: Source folder '{source_path}' does not exist.")
    return pending_bytes

  print(f"\nProcessing directory: '{source_path}'...")
  processed_count = 0

  for root, _, files in os.walk(source_path):
    for filename in files:
      file_path = os.path.join(root, filename)
      file_size = os.path.getsize(file_path)

      relative_path = os.path.relpath(file_path, source_path)
      dest_dir = os.path.join(DESTINATION_BASE_PATH, os.path.dirname(relative_path))
      os.makedirs(dest_dir, exist_ok=True)

      dest_path_normal = os.path.join(dest_dir, filename)
      dest_path_zip = os.path.join(dest_dir, filename + ".zip")

      # Skip if file already exists in target destination
      if os.path.exists(dest_path_normal) or os.path.exists(dest_path_zip):
        continue

      # Process file based on size
      processed_count += 1
      if file_size <= SIZE_THRESHOLD_BYTES:
        shutil.copy2(file_path, dest_path_normal)
        print(f"Copied: {filename} ({file_size / (1024*1024):.2f} MB)")
      else:
        print(f"Zipping: {filename} ({file_size / (1024*1024):.2f} MB)")
        splitzip.create(dest_path_zip, [file_path], split_size=SIZE_THRESHOLD_MIB)

      pending_bytes += file_size

      # Check push threshold
      if pending_bytes >= GIT_PUSH_THRESHOLD_BYTES:
        git_commit_and_push(f"Sync batch - reached {pending_bytes / (1024*1024):.2f} MB processed")
        pending_bytes = 0

  print(f"Finished directory. New files processed: {processed_count}")
  return pending_bytes


# --- Main Execution Loop --- #
accumulated_bytes = 0

for folder in SOURCE_FOLDERS:
  full_source_path = os.path.join("/content/drive/My Drive/upload", folder, REPO_DATE)
  accumulated_bytes = process_folder(full_source_path, accumulated_bytes)

# Push any remaining files that didn't hit the 500 MB threshold
if accumulated_bytes > 0:
  git_commit_and_push("Sync final remaining media files batch")
else:
  print("\nNo remaining uncommitted files to push.")