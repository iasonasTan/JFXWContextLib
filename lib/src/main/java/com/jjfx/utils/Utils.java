package com.jjfx.utils;

class Utils {
    /**
     * Converts given StackTrace into a string.
     * @param stackTrace stack trace as array.
     * @return String with given color that represents given stacktrace.
     */
    public static String stacktraceToString(StackTraceElement[] stackTrace) {
        StringBuilder messageBuilder = new StringBuilder();
        for (StackTraceElement stackTraceElement: stackTrace) {
            messageBuilder
                    .append(stackTraceElement.toString())
                    .append('\n');
        }
        return messageBuilder.toString();
    }
}
