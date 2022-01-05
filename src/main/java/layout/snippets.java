/*

public void readFile(String path){
	try{
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line;
		while((line = reader.readLine()) != null){
			System.out.println(line);
		}
	}catch(IOException e){
		e.printStackTrace();
	}
	catch(FileNotFoundException e){
		e.printStackTrace();
	}
	finally{
		if(reader != null){
			try{
				reader.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}		
		}
	}
}

public void writeFile(String path){
	try{
		FileWriter writer = new FileWriter(path);
		writer.write("Content");
		writer.flush();
		writer.close();
	}
	catch(IOException e){
		e.printStackTrace();
	}
}

public void writeFileBit(String path){
    FileOutputStream fileOutputStream = null;
    try{
        fileOutputStream = new FileOutputStream(path);
        fileOutputStream.write("Hello world".getBytes());
    } catch (IOException e){
        e.printStackTrace();
    } finally{
        try{
            if(fileOutputStream != null)
                fileOutputStream.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

public void readFileBit(String path)
    FileInputStream fileInputStream = null;
    try{
        fileInputStream = new FileInputStream(path);
        int b;
        while((b = fileInputStream.read()) != -1){
            System.out.print((char)b);
        }
    } catch (IOException e){
        e.printStackTrace();
    } finally{
        try{
            if(fileInputStream != null)
                fileInputStream.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}


public void readUrl(Sting url){
    try {
        URL u = new URL(url);
        BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
        String line;
        while((line = in.readLine()) != null){
            taSource.append(line+"\n");
        }

//			taSource.read(in,null);

        in.close();
    } catch (MalformedURLException ex) {
        
    } catch (IOException ex) {
    } 
}


 */
