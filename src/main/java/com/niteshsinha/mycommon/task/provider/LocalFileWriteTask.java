package com.niteshsinha.mycommon.task.provider;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.niteshsinha.mycommon.exception.TaskException;

public class LocalFileWriteTask extends AbstractTask {
	
	private String fileName;
	private Object content;
	//TODO: This attribute is not needed... Remove it
	private boolean append = false;
	private String taskType = this.getClass().getName();
	
	/*
	 * Constructor
	 * @param fileName file to be created
	 * @param content Content of the file
	 */
	public LocalFileWriteTask(String fileName, Object content) {
		this.fileName = fileName;
		this.content = content;
	}
	
	public LocalFileWriteTask(String fileName, Object content, boolean append) {
		this.fileName = fileName;
		this.content = content;
		this.append = append;
	}
	
	public LocalFileWriteTask(String filename, Object content, long ms) {
		this.fileName = filename;
		this.content = content;
		this.timeToExecute = ms;
	}
	
	public LocalFileWriteTask(String fileName, Object content, boolean append, long ms) {
		this.fileName = fileName;
		this.content = content;
		this.append = append;
		this.timeToExecute = ms;
	}
	
	public LocalFileWriteTask(Object task) {		
	}
	
	/*
	 * Writes content into a file
	 * (non-Javadoc)
	 * @see com.symantec.bloc.task.LocalTask#execute()
	 */
	public void execute() throws TaskException{
		BufferedWriter bw = null;
		try {
		    bw = new BufferedWriter(new FileWriter(this.fileName, this.append));
		    //For the Time Being lets consider writing the
		    //Object's toString implementation into the file
		    //TODO: Not a good idea to dump the toString Implementation
		    //TODO: Consider Serialization
		    //System.out.println("About to write " + content.toString());
		    bw.write(content.toString());
		    if(this.append) { bw.write("\r\n"); }		    	
		    bw.close();
		    
		} catch(IOException ioex) {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					//Ignore....
					e.printStackTrace();
				}
			}
			throw new TaskException(this.taskType + "Exception " + ioex.getMessage());
		}
	}
	
//	public static void main(String[] args) {
//		String str = "asdsda: asdasdsda" + "\r\n" + "adfggf: dghdfg" + "\r\n" + "\r\n" + "asdasjkdhasjdhas";
//		Thread t = new Thread(new LocalFileWriteTask("/tmp/FileFromLocalTask.txt", str));
//		try {
//			t.start();		
//			t.join();
//		} catch (InterruptedException iex) {
//			System.out.println(iex.getMessage());
//			System.out.println(iex.getStackTrace().toString());
//		}
//	}
	

}
