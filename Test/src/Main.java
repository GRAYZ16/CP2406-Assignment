import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		for(int i = 3; i > -1; i--)
		{
			if(i == 0)
			{
				System.out.println(3);
			}
			else
			{
				System.out.println(i - 1);
			}
		}
		for(int i = 0; i < 4; i++)
		{
			 System.out.println((i + 1) % 4);
		}

		int i = 3;
		i = (i + 1) % 4;
		System.out.println(i);

		String test = "1234";

		String[] split = test.split(",");
		for(String section : split)
		{
			System.out.println(section);
		}

		Scanner scanner = new Scanner(System.in);
		System.out.print(scanner.nextLine());
		System.out.println("Hello2");
		System.out.println(0 % 2);

	}
}
