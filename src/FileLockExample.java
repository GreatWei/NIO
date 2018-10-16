
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileLockExample {
	public static void main(String[] args) throws IOException {
		String input = "* end of the file.";
		System.out.println("Input string to the test file is: " + input);
		ByteBuffer buf = ByteBuffer.wrap(input.getBytes());
		String fp = "testout-file.txt";
		Path pt = Paths.get(fp);
		FileChannel fc = FileChannel.open(pt, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
		System.out.println("File channel is open for write and Acquiring lock...");
	//	fc.position(fc.size() - 1); // position of a cursor at the end of file
		FileLock lock = fc.lock(fc.size()-1,0,true);
		System.out.println("The Lock is shared: " + lock.isShared());
		fc.write(buf);
		fc.close(); // Releases the Lock
		System.out.println("Content Writing is complete. Therefore close the channel and release the lock.");
		PrintFile.print(fp);
	}
}
