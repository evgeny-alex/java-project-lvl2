package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {

    private static final int SUCCESS_EXIT_CODE = 0;

    @Option(names = {"-f", "--format"},
            defaultValue = "stylish",
            paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String formatType;

    @Parameters(paramLabel = "filepath1",
            index = "0",
            description = "path to first file")
    private String filePath1;

    @Parameters(paramLabel = "filepath2",
            index = "1",
            description = "path to second file")
    private String filePath2;

    @Override
    public Integer call() throws Exception {
        String formattedString = Differ.generate(filePath1, filePath2, formatType);
        System.out.println(formattedString);
        return SUCCESS_EXIT_CODE;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
