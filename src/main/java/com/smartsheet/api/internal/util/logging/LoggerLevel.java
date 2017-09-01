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



import org.slf4j.Logger;

/**
 * IoC-like way of dealing with loggers (the level logs data into the logger);
 * unfortunately SLF4J doesn't even have a Level enum :-/
 */
public enum LoggerLevel {
    Trace {
        @Override
        public void log(Logger logger, String format, Object... args) {
            logger.trace(format, args);
        }
    }, Debug {
        @Override
        public void log(Logger logger, String format, Object... args) {
            logger.debug(format, args);
        }
    }, Info {
        @Override
        public void log(Logger logger, String format, Object... args) {
            logger.info(format, args);
        }
    }, Warn {
        @Override
        public void log(Logger logger, String format, Object... args) {
            logger.warn(format, args);
        }
    }, Error {
        @Override
        public void log(Logger logger, String format, Object... args) {
            logger.error(format, args);
        }
    }, Disabled {
        @Override
        public void log(Logger logger, String format, Object... args) {
            ; // no-op
        }
    };

    abstract public void log(Logger logger, String format, Object... args);

    public static LoggerLevel getLowestLevel(Logger logger) {
        if (logger.isTraceEnabled()) {
            return Trace;
        }
        if (logger.isDebugEnabled()) {
            return Debug;
        }
        if (logger.isInfoEnabled()) {
            return Info;
        }
        if (logger.isWarnEnabled()) {
            return Warn;
        }
        if (logger.isErrorEnabled()) {
            return Error;
        }
        return Disabled;
    }

    /** utility method so callers don't have to worry about invalid/malformed levels */
    public static LoggerLevel getLevel(String level, LoggerLevel def) {
        if (level == null || level.isEmpty()) {
            return def;
        }
        try {
            return LoggerLevel.valueOf(level);
        } catch (Exception ex) {
            return def;
        }
    }
}
