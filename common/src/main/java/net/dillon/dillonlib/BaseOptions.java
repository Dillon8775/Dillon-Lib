package net.dillon.dillonlib;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dillon.dillonlib.platform.PlatformGetter;
import net.dillon.dillonlib.platform.Statics;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.function.Consumer;

/**
 * A base class for registering options on different environment sides.
 */
public abstract class BaseOptions<T> {
    private final Gson GSON = createGson();
    private String fileName;
    private File file;
    private File customDir;
    protected T instance;

    /**
     * Constructor which initializes the file name and the instance of the options.
     */
    public BaseOptions(String fileName) {
        this.fileName = fileName;
        this.instance = createDefault();
    }

    /**
     * Initializes this class.
     */
    public static void i_() {
    }

    /**
     * Returns the config directory to be resolved.
     */
    public abstract String configDir();

    /**
     * Returns the type to get the options from.
     */
    protected abstract T createDefault();

    /**
     * Returns the class to get the options from.
     */
    protected abstract Class<T> getConfigClass();

    /**
     * Creates the {@code GSON reader,} which reads options correctly.
     */
    public Gson createGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    }

    /**
     * Runs a safe check through all options to ensure no issues.
     * <p>Preforms a {@code "safe check"} on all the Speedrunner Mod options, and makes sure that they are valid and safe to run in-game.
     * <p>If an option is broken or invalid, and it is not recommended to run, the user will automatically boot into the Safe boot screen.</p>
     */
    protected abstract void safeCheck();

    /**
     * Gets the instance of options.
     */
    public T getInstance() {
        return this.instance;
    }

    /**
     * Sets current instance to a new option instance.
     */
    public void setInstance(T instance) {
        this.instance = instance;
    }

    /**
     * Sets and creates a new file.
     */
    public void setFileName(String fileName) {
        this.file = null;
        this.fileName = fileName;
    }

    /**
     * Creates a new custom directory for the server file.
     */
    public void setCustomDirectory(File dir) {
        this.customDir = dir;
        this.file = null;
    }

    /**
     * Clears custom directory.
     */
    public void clearCustomDirectory() {
        this.customDir = null;
        this.file = null;
    }

    /**
     * Updates a config option and saves it.
     */
    public void update(Consumer<T> options) {
        options.accept(this.instance);
        this.save();
    }

    /**
     * Saves {@code this} config.
     */
    public void save() {
        try (FileWriter writer = new FileWriter(getConfigFile())) {
            writer.write(GSON.toJson(this.instance));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads {@code this} config.
     */
    public void load() {
        File configFile = getConfigFile();
        if (!configFile.exists()) {
            this.instance = createDefault();
        } else {
            try (FileReader reader = new FileReader(configFile)) {
                this.instance = GSON.fromJson(reader, getConfigClass());
            } catch (Exception e) {
                e.printStackTrace();
                this.instance = createDefault();
            }
        }
        this.safeCheck();
        this.save();
    }

    /**
     * Gets {@code this} config file.
     */
    public File getConfigFile() {
        if (this.file == null) {
            File baseDir = (this.customDir != null)
                    ? this.customDir
                    : Statics.CONFIG_DIR.resolve(this.configDir()).toFile();

            baseDir.mkdirs();
            this.file = new File(baseDir, this.fileName);
        }
        return this.file;
    }
}