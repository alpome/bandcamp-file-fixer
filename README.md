# Bandcamp File Fixer - BCFF

BCFF is a Java utility to rename files downloaded from Bandcamp 
en masse. Most Bandcamp files have the artist and album names prepended 
to the track title. This tool takes a given directory and renames the 
files, removing the artist and album names and leaving only the name of 
the track, optionally renaming the files of any nested folders as well. 

### Options
    -h          Print this help text and exit.
    -r          Recurse through sub-folders nestled under provided directory. Default is false.
    -ar         Remove artist name only. Default is false. (Not yet implemented.)
    -al         Remove album name only. Default is false. (Not yet implemented.)

