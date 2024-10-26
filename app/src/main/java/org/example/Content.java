package org.example;

public class Content {

    private final String groupId;
    private final String artifactId;
    private final String version;

    private String extension = "";

    public Content(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public Content setExt(String extension) {
        this.extension = extension;
        return this;
    }

    public String groupId() {
        return this.groupId;
    }

    public String artifactId() {
        return this.artifactId;
    }

    public String version() {
        return this.version;
    }

    public String extension() {
        return this.extension;
    }

}
