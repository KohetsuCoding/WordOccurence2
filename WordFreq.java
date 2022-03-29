package application;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordFreq {
	public void wordCount() {
		System.out.println("Calculating word frequency...");
		String text = null;
		
		try  {
			String fileName = "https://www.gutenberg.org/files/1065/1065-h/1065-h.htm";
			URL url = new URL(fileName);
			URLConnection conn = url.openConnection();
			LineNumberReader rdr = new LineNumberReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb1 = new StringBuilder();
			
			for (text = null; (text = rdr.readLine()) !=null;) {
				if (rdr.getLineNumber() > 11 & rdr.getLineNumber() < 140) {
					sb1.append(text).append(File.pathSeparatorChar);
				} else if (rdr.getLineNumber() > 140) {
					break;
				}
			}
			String textLC = sb1.toString().toLowerCase();
			Pattern pttrn = Pattern.compile("[a-z]+");
			Matcher mtchr = pttrn.matcher(textLC);
			TreeMap<String, Integer> freq = new TreeMap<>();
			int longest = 0;
			
			while (mtchr.find()) {
				String word = mtchr.group();
				int letters = word.length();
				
				if (letters > longest) {
					longest = letters;
				}
				if (freq.containsKey(word)) {
					freq.computeIfPresent(word,  (w, c) -> Integer.valueOf(c.intValue() +1));
				}
				else {
					freq.computeIfAbsent(word,  (w) -> Integer.valueOf(1));
				}
			}
			String format = "%-" + longest + "s = %2d%n";
			freq.forEach((k, v) -> System.out.printf(format,  k, v));
			rdr.close();
		
		} catch (MalformedURLException ee) {
			ee.printStackTrace();
		}
		  catch (IOException eee) {
			eee.printStackTrace();
		}
	}
}
