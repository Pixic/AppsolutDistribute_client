import java.net.*;
import java.io.*;

class ReadRemoteFile {
	public static void main( String[] arg ) {
		URL url;	
		URLConnection uc;
		try {
			url = new URL(arg[ 0 ]);
			uc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String line;
			while ( (line = in.readLine()) != null )
				System.out.println(line);
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
