import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NIOTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Selector selector = Selector.open();
		FileInputStream fileInputStream = new FileInputStream("json.txt");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
		 System.out.println("Reading the Line of testout.txt file: \n" + bufferedReader.readLine());

		ReadableByteChannel readableByteChannel = fileInputStream.getChannel();
		File files = new File("json-out.txt");
		FileOutputStream fileOutputStream = new FileOutputStream(files);
		WritableByteChannel writableByteChannel = fileOutputStream.getChannel();
		copyData(readableByteChannel, writableByteChannel);
		readableByteChannel.close();
		writableByteChannel.close();
		System.out.println("Copy Data finished.");
		DatagramChannel gChannel = DatagramChannel.open();// 数据报通道可以通过UDP(用户数据报协议)通过网络读取和写入数据。它使用工厂方法来创建新对象。
		SocketChannel ch = SocketChannel.open();
		int someport = 10086;
		// ch.connect(new InetSocketAddress("somehost", someport));//
		// 数据报通道可以通过TCP(传输控制协议)通过网络读取和写入数据。 它还使用工厂方法来创建新对象。
		// OpenOption options;
		Path file = null;
		file = Paths.get("json-out.txt");
		InputStream inputStream = Files.newInputStream(file);
	}

	private static void copyData(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocateDirect(20 * 1024);
		while (src.read(buffer) != -1) {
			// The buffer is used to drained
			buffer.flip();
			// keep sure that buffer was fully drained
			while (buffer.hasRemaining()) {
				dest.write(buffer);
			}
			buffer.clear(); // Now the buffer is empty, ready for the filling
		}
	}

}
