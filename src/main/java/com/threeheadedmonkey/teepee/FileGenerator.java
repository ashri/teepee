package com.threeheadedmonkey.teepee;

import com.threeheadedmonkey.teepee.entity.Consolidator;
import com.threeheadedmonkey.teepee.entity.Item;
import com.threeheadedmonkey.teepee.io.FileReader;
import com.threeheadedmonkey.teepee.web.DailyTasksDecorator;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.Collection;

/**
 * Take a Taskpaper file and generate an HTML file
 */
public class FileGenerator {

    private File taskpaperFile;
    private File destinationDirectory;
    private String label;

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: java -jar teepee.jar <file-to-generate-with> <destination-directory>");
            System.exit(1);
        }
        String taskpaperFile = args[0];
        String destinationDirectory = args[1];

        FileGenerator self = new FileGenerator(taskpaperFile, destinationDirectory);
        if (!self.valid()) {
            System.exit(2);
        }
        System.out.print(self.logStart());
        self.generate();
        System.out.println(self.logFinish());
    }

    public FileGenerator(String taskpaperFile, String destinationDirectory) {
        this.taskpaperFile = new File(taskpaperFile);
        this.destinationDirectory = new File(destinationDirectory);
    }

    private boolean valid() {
        if (!taskpaperFile.exists()) {
            System.err.println("Taskpaper file to process not found");
            return false;
        }
        if (!destinationDirectory.exists()) {
            System.err.println("Destination directory not found");
            return false;
        }
        if (!destinationDirectory.isDirectory()) {
            System.err.println("Destination directory is not a directory");
            return false;
        }
        return true;
    }

    private String logStart() {
        return "Generating Taskpaper " + taskpaperFile + " as HTML at " + destinationDirectory + " ...";
    }

    private String logFinish() {
        return " done.";
    }

    private void generate() throws FileNotFoundException {

        this.label = taskpaperFile.getName().substring(0, taskpaperFile.getName().lastIndexOf("."));

        Collection<Item> items = new FileReader().read(new java.io.FileReader(taskpaperFile));
        Collection<Item> consolidatedItems = new Consolidator(items).consolidate();
        DailyTasksDecorator dailyTasks = new DailyTasksDecorator(consolidatedItems);

        runVelocity(dailyTasks);
    }

    private void runVelocity(DailyTasksDecorator dailyTasks) {
        Velocity.init();

        VelocityContext context = new VelocityContext();
        context.put("key", label);
        context.put("tasks", dailyTasks);

        Template template = Velocity.getTemplate("templates/tasks.vm");

        StringWriter sw = new StringWriter();
        template.merge(context, sw);

        System.out.println("=========");
        System.out.println(sw.toString());
        System.out.println("=========");
    }

}
