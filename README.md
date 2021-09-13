# Bandcamp File Fixer - BCFF

BCFF is a Java CLI utility to rename files downloaded from Bandcamp 
en masse. Most Bandcamp files have the artist and album names prepended 
to the track title. This tool takes a given directory and renames the 
files, removing the artist and album names and leaving only the name of 
the track. 

### Options
    -h          Print this help text and exit
    -v          Print version number and exit
    -r          Recurse through sub-folders nestled under provided directory. Default is false.
    -ar         Remove artist name only. Default is false. (Not yet implemented.)
    -al         Remove album name only. Default is false. (Not yet implemented.)
    -m          Rename music files only. Default is false. (Not yet implemented.)

### Changes/Fixes to be Made

Due to how Java handles Unicode input, the provided directory cannot contain Unicode 
characters.

Currently this relies on the folder being titled in a specific way that is common to most Bandcamp 
downloads. However, should the file have been renamed or Bandcamp named the folder different than 
this common format (which is sometimes the case), this tool will fail.
