package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {
    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @param views
     *            the views to attach
     */
    public DrawNumberApp(final DrawNumberView... views) {
        /*
         * Side-effect proof
         */
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view: views) {
            view.setObserver(this);
            view.start();
        }

        final Configuration.Builder configFromFile = new Configuration.Builder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("config.yml")))) {
            String setting;
            while ((setting = reader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(setting, ": ");
                switch (stringTokenizer.nextToken()) {
                    case "minimum":
                        configFromFile.setMin(Integer.valueOf(stringTokenizer.nextToken()));
                        break;
                    case "maximum":
                        configFromFile.setMax(Integer.valueOf(stringTokenizer.nextToken()));
                        break;
                    case "attempts":
                        configFromFile.setAttempts(Integer.valueOf(stringTokenizer.nextToken()));
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            displayError(e.getMessage());
        }

        final Configuration configuration = configFromFile.build();
        if (configuration.isConsistent()) {
            this.model = new DrawNumberImpl(configuration.getMin(), configuration.getMax(), configuration.getAttempts());
        } else {
            displayError("Inconsistent configuration, proceeding with Default");
            final Configuration defaultConfig = new Configuration.Builder().build(); 
            this.model = new DrawNumberImpl(defaultConfig.getMin(), defaultConfig.getMax(), defaultConfig.getAttempts());
        }
    }

    private void displayError(final String err) {
        for (final DrawNumberView view: views) {
            view.displayError(err);
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp(new DrawNumberViewImpl());
    }

}
