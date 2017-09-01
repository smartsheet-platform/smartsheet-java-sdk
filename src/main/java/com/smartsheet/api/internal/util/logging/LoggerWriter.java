package com.smartsheet.api.internal.util.logging;

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


import com.smartsheet.api.internal.util.Util;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.Writer;

/**
 * an adapter to route character data from an interface that expects a Writer into SLF4J
 * NOTE - doesn't actually log to the Logger until flush() (or close()) and only if the buffer isn't empty
 */
public class LoggerWriter extends Writer {
    // because DefaultHttpClient.errorLoggerWriter is static we need to be careful with thread-specific buffers
    // we can also create non-static instances of LoggerWriter, which is why threadBuffer isn't static
    private final ThreadLocal<StringBuilder> threadBuffer = new ThreadLocal<StringBuilder>(){
        @Override
        protected StringBuilder initialValue() {
            return new StringBuilder();
        }
    };
    private final Logger logger;
    private final LoggerLevel level;

    public LoggerWriter(final Logger logger, final LoggerLevel level) {
        super();
        this.logger = Util.throwIfNull(logger);
        this.level = Util.throwIfNull(level);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        threadBuffer.get().append(cbuf, off, len);
    }

    /**
     * this is the point at which we log (which is why we're going to want an auto/explicit flush - otherwise lots of
     * things will get included in a single log message - but how else to detect the end of a message?)
     * @throws IOException
     */
    @Override
    public void flush() throws IOException {
        StringBuilder buf = threadBuffer.get();
        if (buf.length() != 0) {
            level.log(logger, "{}", buf);
        }
        threadBuffer.remove();  // new buffer will be created for this thread next time
    }

    @Override
    public void close() throws IOException {
        flush();
    }
}
