# Bandcamp File Fixer - BCFF

BCFF is a Java CLI utility to rename files downloaded from Bandcamp 
en masse. Most Bandcamp files have the artist and album names prepended 
to the track title. This tool takes a given directory and renames the 
files, removing the artist and album names and leaving only the name of 
the track. 

Currently, due to how Java handles Unicode input, the provided directory 
cannot contain Unicode characters. I believe a solution exists and will be
added in a later revision. I need to research more first.

### Options
    -h          Print this help text and exit
    -v          Print version number and exit
    -r          Recurse through sub-folders nestled under provided directory. Default is false.
    -ar         Remove artist name only. Default is false. (Not yet implemented.)
    -al         Remove album name only. Default is false. (Not yet implemented.)
    -m          Rename music files only. Default is false. (Not yet implemented.)


