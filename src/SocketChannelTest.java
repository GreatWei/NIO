import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SocketChannelTest {

	public void SocketChannelExample() throws IOException {
		SocketChannel sc = SocketChannel.open();
		sc.connect(new InetSocketAddress("localhost", 9099));

		ByteBuffer bb = ByteBuffer.allocate(84);
		int bytesRead = sc.read(bb);

		String newData = "The new String is writing in a file ..." + System.currentTimeMillis();
		ByteBuffer bbw = ByteBuffer.allocate(48);
		bbw.clear();
		bbw.put(newData.getBytes());
		bbw.flip();
		while (bbw.hasRemaining()) {
			sc.write(bbw);
		}

	}

	public void ServerSocketChannelExmple() throws IOException {
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(8085));
		while (true) {
			SocketChannel socketChannel = ssc.accept();
			ByteBuffer bb = ByteBuffer.allocate(84);
			int bytesRead = socketChannel.read(bb);

		}
	}
}
