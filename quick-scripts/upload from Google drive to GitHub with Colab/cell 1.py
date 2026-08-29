from google.colab import userdata
import os

# Retrieve SSH keys from Colab secrets
private_key = userdata.get("OPENSSH_PRIVATE_KEY").replace("\r\n", "\n").strip() + "\n"
public_key = userdata.get("OPENSSH_PUBLIC_KEY").replace("\r\n", "\n").strip() + "\n"

# Ensure the .ssh directory exists
ssh_dir = os.path.expanduser("~/.ssh")
os.makedirs(ssh_dir, exist_ok=True)

# Define paths for the key files
private_key_path = os.path.join(ssh_dir, "id_ed25519")
public_key_path = os.path.join(ssh_dir, "id_ed25519.pub")

# Write the private key and set permissions (0o600)
if private_key:
  with open(private_key_path, "w") as f:
    f.write(private_key)
  os.chmod(private_key_path, 0o600)
  print(f"Private key written to {private_key_path} and permissions set to 600.")
else:
  print("OPENSSH_PRIVATE_KEY not found in Colab secrets.")

# Write the public key and set permissions (0o644)
if public_key:
  with open(public_key_path, "w") as f:
    f.write(public_key)
  os.chmod(public_key_path, 0o644)
  print(f"Public key written to {public_key_path} and permissions set to 644.")
else:
  print("OPENSSH_PUBLIC_KEY not found in Colab secrets.")