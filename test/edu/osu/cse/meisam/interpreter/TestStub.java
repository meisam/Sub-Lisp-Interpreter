package edu.osu.cse.meisam.interpreter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import junit.framework.TestCase;

public abstract class TestStub extends TestCase {

    protected static final int DEFAULT_BUFFER_SIZE = 1000;

    protected static final String TEST_DIR = "testfiles";

    protected static final String SMOKE_TEST_FILE_NAME = "smoke-test.input";

    protected static final File SMOKE_TEST_FILE_PATH = new File(
            TestStub.TEST_DIR + File.separator + TestStub.SMOKE_TEST_FILE_NAME);

    public TestStub() {
        super();
    }

    protected File[] getFiles(final String dir) {
        final File directory = new File(dir);
        return directory.listFiles();
    }

    protected boolean isTestFile(final File fileName) {
        return fileName.getName().endsWith(".input");
    }

    protected String readfromFile(final File file) throws IOException {
        final Reader input = new FileReader(file);
        final StringBuffer fileContent = new StringBuffer(
                TestStub.DEFAULT_BUFFER_SIZE);

        final char[] buffer = new char[TestStub.DEFAULT_BUFFER_SIZE];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            fileContent.append(buffer, 0, n);
        }
        input.close();
        return fileContent.toString();
    }

}