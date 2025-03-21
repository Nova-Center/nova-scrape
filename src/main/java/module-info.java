module fr.nova.novascrape {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens fr.nova.novascrape to javafx.fxml;
    exports fr.nova.novascrape;
}