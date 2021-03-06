package io.sdkman.broker.download;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.stream;

public enum Platform {
    FREE_BSD(newArrayList("FreeBSD")),
    LINUX_32(newArrayList("Linux32")),
    LINUX_64(newArrayList("Linux", "Linux64")),
    MAC_OSX(newArrayList("Darwin")),
    WINDOWS_32(newArrayList("MINGW32")),
    WINDOWS_64(newArrayList("CYGWIN", "MINGW64", "MSYS")),
    SUN_OS(newArrayList("SunOS"));

    List<String> ids;

    Platform(List<String> ids) {
        this.ids = ids;
    }

    public static Optional<Platform> of(String id) {
        return Optional.ofNullable(id)
                .map(String::toLowerCase)
                .flatMap(pid -> stream(Platform.values())
                        .filter(platform -> platform.ids.stream().map(String::toLowerCase).anyMatch(pid::startsWith))
                        .findFirst());
    }

    public String id() {
        return ids.get(0);
    }
}
