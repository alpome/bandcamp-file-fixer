import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.exit;
import static java.lang.System.setOut;

/***
* @author Alexander Merluzzi
* @date February 27 2020
* @title Bandcamp File Fixer - BCFF
* This is a small utility with the purpose of renaming songs downloaded from
* Bandcamp to remove the artist and album name prepended to every track.
*/

class BCFF{
	final static String REGEX = ".* - .* - .*";
	final static String PATH = "";
	final static double ver = 0.8;
	//static ArrayList<String> list = new ArrayList<String>();
	static FilenameFilter fnf = new FilenameFilter() {
		public boolean accept(File dir, String filename) {
			if (filename.endsWith(".zip"))
				System.out.println("Zip archive - skipped");
			return !filename.endsWith(".zip");
		}
	};

    /**
     *
     * @param args
     */
	public static void main(String[] args){
		Scanner in = new Scanner(System.in, "UTF-8");
		File folder = null;
		boolean recurse = true, rmv_art = false, rmv_alb = false;

		//@TODO Finish code to use CLI arguments rather than Scanner
		if (args.length <= 0) {
			System.out.println("No arguments supplied.");
			exit(1);
		} else {
			for (int i = 0; i < args.length; i++) {
				switch (args[i]) {
					case "-r":
						recurse = Boolean.parseBoolean(args[i + 1]);
						System.out.println("Recurse flag set to " + recurse);
						i++;
						break;
                    case "-ar":
                        //set remove artist flag to either TRUE or FALSE
						rmv_art = true;
                        break;
                    case "-al":
                        //set remove album flag to either TRUE or FALSE
						rmv_alb = true;
                        break;
                    case "-v":
						System.out.println("BCFF version " + ver);
                    case "-h":
						System.out.println("Options:");
						System.out.println("-h			Print this text and exit");
						System.out.println("-v			Print version number and exit");
						System.out.println("-r VALUE	Recurse through sub-folders nestled under provided directory. Default is true.");
						System.out.println("-ar			Remove artist name only. Default is false. (Not yet implemented.)");
						System.out.println("-al			Remove album name only. Default is false. (Not yet implemented.)");
						System.exit(0);
                    default:
                        //Check if input is valid file directory and begin process
						//otherwise quit program
						String dir = args[i];
						folder = new File(dir);
						if (!folder.exists() && !folder.isDirectory()){
							System.out.println("Path provided - " + dir + " - either does not exist or is not a directory.");
							System.exit(1);
						}
				}
			}
		}

		/*
		Loop to get valid directory as input

		do{
			System.out.print("Provide path to directory: ");
			String dir = in.nextLine();
			folder = new File(dir);
			System.out.println(folder);
			System.out.println(folder.exists());
			System.out.println(folder.isDirectory());
			System.out.println(folder.isFile());
			if (!folder.exists() && !folder.isDirectory()){
				System.out.println("Path provided - " + dir + " - either does not exist or is not a directory.");
				folder = null;
			}
		} while (folder == null);
		in.close();*/

		/*
		Checks if directory contains any files or subdirectories, ignoring .zip archives
		Calls file tree stepping function if files/subdirectories are found
		 */
		try {
			File[] ff = folder.listFiles(fnf);
			if (ff != null && ff.length > 0){
				checkTree(ff);
			} else {
				if (ff == null)
					System.out.println("No file list found");
				else if (ff.length <= 0)
					System.out.println("File list length is less than or equal to 0: " + ff.length);
			}
		} catch (NullPointerException npe){
			System.out.println("Directory is empty");
		}
	}

    /**
     * A function to check the provided file list and either call itself on any subdirectories
     * or appropriately rename any music files it may find
     * @param files
     */
	static void checkTree(File[] files){
		Pattern n = Pattern.compile(REGEX);
		Matcher m;
		for (File f: files){
			m = n.matcher(f.getName());
			
			if (f.isDirectory()){
				checkTree(f.listFiles(fnf));
			} else {
				//if (m.matches())
				rename(f);
			}
		}
	}

    /**
     * A function to check that a provided file is (a) a supported music file and
     * (b) conforms to normal Bandcamp naming conventions.
     * It then renames the file according to how the user has specified.
     * @param file
     */
	static void rename(File file){
		String name = file.getName(), fixed_name = null;
		String path = file.getPath(), fixed_path = path.replace(name, "");
		String parent_f = file.getParentFile().getName();
		if (name.contains(parent_f)){
			System.out.println("Renaming " + name);
			fixed_name = name.replace(parent_f, "").substring(3);
			file.renameTo(new File(fixed_path + fixed_name));
			System.out.println("Renamed to " + fixed_name);
		}
	}

    /**
     * Checks whether a provided file is a supported music file
     * @param file
     * @return
     */
    //@TODO finish
	static Boolean unsupportedExt(File file){
		String[] exts = new String[]{"jpg","bmp","png","pitt","txt","exe"};
		List<String> list = Arrays.asList(exts);
		//return list.contains(file.getExtension());
		return true;
	}
}