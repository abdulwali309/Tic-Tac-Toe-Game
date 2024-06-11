public class Data {
    private String configuration; // Configuration data stored as a string
    private int score; // Score associated with the configuration

    // Constructor to initialize configuration and score
    public Data(String configuration, int score) {
        this.configuration = configuration; // Initialize configuration
        this.score = score; // Initialize score
    }

    // Getter method to retrieve the configuration
    public String getConfiguration() {
        return configuration; // Return the configuration
    }

    // Getter method to retrieve the score
    public int getScore() {
        return score; // Return the score
    }
}