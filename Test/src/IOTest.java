import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class IOTest
{
	public static void main(final String[] args)
	{
		try
		{
			List<String> lines = Arrays.asList("GRAYZ16, 156", "Sara,133");
			Path file = Paths.get("scores.txt");
			Files.write(file, lines, Charset.forName("UTF-8"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
