package petersonLabs.hackerRank.tester;

import java.io.*;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Main {
	private static final String DEBUG_ADDRESS = "*:3000";
	private static final boolean DEBUG = true;

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("=============== Hacker Rank Exercise Tester ===============");
		runTest("petersonLabs.hackerRank.exercises.AddNumbers", "1\r\n0", "0");
		runTest("petersonLabs.hackerRank.exercises.AddNumbers", "1\n1", "1");
		runTest("petersonLabs.hackerRank.exercises.AddNumbers", "2\n1 0", "1");
		runTest("petersonLabs.hackerRank.exercises.AddNumbers", "2\n1 1", "2");
		runTest("petersonLabs.hackerRank.exercises.AddNumbers", "6\n1 2 3 4 5 6", "21");
	}

	public static void runTest(String className, String input, String expectedOutput) throws IOException, InterruptedException {
		File inputFile = createFile(false);
		File outputFile = createFile(true);
		try {

			try (FileWriter writer = new FileWriter(inputFile)) {
				writer.write(input);
			}

			String additionalArgs = DEBUG ? String.format(" -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,quiet=y,address=%s", DEBUG_ADDRESS) : "";
			String command = String.format("java%s -cp %s %s", additionalArgs, System.getProperty("java.class.path"), className);

			ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
			processBuilder
				.redirectError(ProcessBuilder.Redirect.INHERIT)
				.redirectOutput(ProcessBuilder.Redirect.to(outputFile))
				.redirectInput(ProcessBuilder.Redirect.from(inputFile));

			Process process = processBuilder.start();
			process.waitFor(10, TimeUnit.MINUTES);

			String output = getFileContents(outputFile);

			System.out.println(String.format("=========== Test: %s ===========", className));
			System.out.println(String.format("input: %s", input));
			if (output.equals(expectedOutput)) {
				System.out.println("Result: SUCCEEDED");
			} else {
				System.err.println("Result: FAILED");
				System.err.println(String.format("expected output: %s", expectedOutput));
				System.err.println(String.format("  actual output: %s", output));
			}

			System.out.println("===================================================");

		} finally {
			if(inputFile.exists())
				inputFile.delete();
			if(outputFile.exists())
				outputFile.delete();
		}
	}

	private static File createFile(boolean isOutputFile) throws IOException {
		String suffix = "-" + (isOutputFile ? "out" : "in") + ".txt";
		File file = new File (UUID.randomUUID() + suffix);
		file.createNewFile();
		file.setWritable(true);
		return file;
	}

	private static String getFileContents(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();

		return new String(data, "UTF-8");
	}
}
