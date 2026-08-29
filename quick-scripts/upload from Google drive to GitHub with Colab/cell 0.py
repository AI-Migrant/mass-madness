"""
This is actually a readme, not a real cell.
Hope that you will find these scripts useful, especially if you are a fellow victims of gangstalking and/or other state crimes.
The process can be further automated, but the author will just leave the script as is.

The author use Google Colab Notebook since he doesn't has a laptop or PC.
This instruction and the scripts are also for running on Colab.

The parts of code surrounded by pairs of quotation marks are called string.
Understand "string" as text in programming context. You will find them in a pair of quotation marks in most programming language.

To use the scripts to push files from Google Drive to GitHub, you will need to authorise with a token or ssh key, my scripts use ssh.
Use the terminal on Colab and follow the instructions at https://docs.github.com/en/authentication/connecting-to-github-with-ssh to create your keys and add the public key to your GitHub account.

Afterwards, add both your private key and public key to secrets on Colab, since the files will be lost shortly after Colab is closed.
Cell 1 will add the files of the ssh keys back to your runtime (which runs your commands and codes).
Just make sure the strings inside the following match the name of your secrets.
userdata.get("OPENSSH_PRIVATE_KEY")
userdata.get("OPENSSH_PUBLIC_KEY")

You only need to run cell 1 once per session. (When the runtime still remember the temporary data.)



Before you run cell 2, make sure you have created your repo on GitHub and change the string stored in REPO_NAME to match your repo name on GitHub.
USER_NAME needs to match your user name on GitHub, since it's used in the url path.
USER_EMAIL shouldn't be free to change as it is used as added information only, if I understand correctly.

Cell 2 will do the rest and push (upload) the files in each folder named in SOURCE_FOLDERS, 
including all subfolders onto your GitHub repo (repository) with the same subfolders structure.
In other words, you only need to put the root (outer most) folder name in SOURCE_FOLDERS. The code contains 2 because I store the files in 2 folders. 

The repo name I use consist of two parts, I leave my actual repo name there for illustration. ()
The first part is the one I change every day, since I create a new repo every day.
REPO_DATE = "20260827"
The second part is fix and I hard coded it on the variable REPO_name.
REPO_NAME = f"persecuted-media-files-{REPO_DATE}"
It means REPO_NAME end up storing the string "persecuted-media-files-20260827 in this example.

Lastly, run cell 2 and it will copy the files to your Colab space, zip them if they are larger than 50 MiB, 
and do necessary process tothe push your files onto your GitHub repo.



The part that mount Google Drive is commented out (each line prefixed with a #) because author's Colab Notebook already mount them.
If you also have them mounted on default, leaving them commented out skip the part where you have to press buttons to confirm mounting.
Remove the "# " if you need to mount the Drive.
Sometimes the script fails. The reason seems to be it failed to move to the correct folder (path) sometimes.
"""