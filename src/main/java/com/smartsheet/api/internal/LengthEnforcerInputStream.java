package com.smartsheet.api.internal;

/*
 * #[license]
 * Smartsheet Java SDK
 * %%
 * Copyright (C) 2014 - 2017 Smartsheet
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * %[license]
 */

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LengthEnforcerInputStream extends FilterInputStream {
    private long expectedLength;
    private long totalBytesRead = 0L;

    public LengthEnforcerInputStream(InputStream inputStream, long expectedLength) {
        super(inputStream);
        this.expectedLength = expectedLength;
    }

    @Override
    public int read() throws IOException {
        checkForTooManyBytes();
        if (totalBytesRead >= expectedLength) {
            throw new EOFException("Incorrect stream length, expected: " + expectedLength + ", actual: " + totalBytesRead);
        }
        int bytesRead = in.read();
        if (bytesRead == -1) {
            checkLength();
        }
        totalBytesRead += bytesRead;
        return bytesRead;
    }

    @Override
    public int read (byte[] b, int off, int len) throws java.io.IOException {
        checkForTooManyBytes();
        int bytesRead = in.read(b, off, len);
        if (bytesRead == -1) {
            checkLength();
        }
        totalBytesRead += bytesRead;
        return bytesRead;
    }

    private void checkForTooManyBytes() throws EOFException {
        if (totalBytesRead > expectedLength) {
            throw new EOFException("Incorrect stream length, expected: " + expectedLength + ", actual: " + totalBytesRead);
        }
    }

    private void checkLength() throws EOFException {
        if (totalBytesRead != expectedLength) {
            throw new EOFException("Incorrect stream length, expected: " + expectedLength + ", actual: " + totalBytesRead);
        }
    }

    @Override
    public synchronized void reset() throws IOException {
        totalBytesRead = 0;
        super.reset();
    }
}
