import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class DjEmocional extends JFrame implements ActionListener {
    private JLabel textoInicio, textoObjetivo, textoGenero;
    private JComboBox<String> selectorEmocion, selectorObjetivo, selectorGenero;
    private JButton enviarButton, omitirButton;
    private JTextArea recomendacionArea;
    private JSlider calificacionSlider;
    private JLabel calificacionLabel;
    private HashMap<String, String> playlists; // Almacena playlists por combinaciones de emociones y objetivos.

    public DjEmocional() {
        super("Dj Emocional");

        // Datos ficticios de playlists. En una implementación real, este sería un conjunto mucho más grande y se obtendría de una base de datos o API.
        playlists = new HashMap<>();
        playlists.put("Felicidad_Intensificar_Rock", "Playlist de Rock Alegre");
        //... [Agregar más playlists según corresponda]

        
        String[] emociones = {"Felicidad", "Tristeza", "Enojo", "Ansiedad", "Estrés"};
        String[] objetivos = {"Intensificar", "Mantener", "Cambiar"};
        String[] generos = {"Rock", "Jazz", "Pop", "Electrónica", "Clásica"};


        textoInicio = new JLabel("¿Qué emoción sientes?");
        textoObjetivo = new JLabel("¿Qué quieres hacer con tu emoción?");
        textoGenero = new JLabel("Selecciona tu género musical favorito");
        selectorEmocion = new JComboBox<>(emociones);
        selectorObjetivo = new JComboBox<>(objetivos);
        selectorGenero = new JComboBox<>(generos);
        enviarButton = new JButton("Enviar");
        omitirButton = new JButton("Omitir Playlist");
        recomendacionArea = new JTextArea(5, 30);
        recomendacionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(recomendacionArea);
        calificacionLabel = new JLabel("Califica la playlist (1-10):");
        calificacionSlider = new JSlider(1, 10, 5);
        calificacionSlider.setMajorTickSpacing(1);
        calificacionSlider.setPaintTicks(true);
        calificacionSlider.setPaintLabels(true);

 
        enviarButton.addActionListener(this);
        omitirButton.addActionListener(e -> generarRecomendacion()); 


        setLayout(new FlowLayout());
        add(textoInicio);
        add(selectorEmocion);
        add(textoObjetivo);
        add(selectorObjetivo);
        add(textoGenero);
        add(selectorGenero);
        add(enviarButton);
        add(omitirButton);
        add(scrollPane);
        add(calificacionLabel);
        add(calificacionSlider);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        generarRecomendacion();
    }

    private void generarRecomendacion() {
        String emocion = (String) selectorEmocion.getSelectedItem();
        String objetivo = (String) selectorObjetivo.getSelectedItem();
        String genero = (String) selectorGenero.getSelectedItem();
        String key = emocion + "_" + objetivo + "_" + genero;

        String recomendacion = playlists.getOrDefault(key, "No tenemos una playlist específica para tu selección. Pero aquí hay una sugerencia genérica para ti: ...");
        recomendacionArea.setText(recomendacion);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DjEmocional());
    }
}
