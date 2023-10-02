import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.net.URI;
import java.awt.Desktop;

public class DjEmocional extends JFrame implements ActionListener {
    private JLabel textoInicio, textoObjetivo, textoGenero;
    private JComboBox<String> selectorEmocion, selectorObjetivo, selectorGenero;
    private JButton enviarButton, omitirButton;
    private JTextArea recomendacionArea;
    private JSlider calificacionSlider;
    private JLabel calificacionLabel;
    private HashMap<String, ArrayList<String>> playlists;
    private HashMap<String, Integer> playlistIndex;

    public DjEmocional() {
        super("Dj Emocional");

        // URLs de playlists reales de Spotify acorde a emociones, objetivos y géneros.
        playlists = new HashMap<>();
        playlists.put("Felicidad_Intensificar_Rock", new ArrayList<String>() {{
            add("https://open.spotify.com/playlist/37i9dQZF1DXcBWIGoYBM5M");
            add("https://open.spotify.com/playlist/37i9dQZF1DXcF6B6QPhFDv");
            add("https://open.spotify.com/playlist/37i9dQZF1DX0XUsuxWHRQd");
        }});
        // ... puedes continuar agregando más combinaciones relevantes ...

        playlistIndex = new HashMap<>();

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
        omitirButton.addActionListener(e -> omitirYRecomendar());

        // Personalización visual
        Color backgroundColor = new Color(232, 240, 254); // Un azul claro amigable
        Color primaryColor = new Color(85, 139, 247); // Azul primario
        Color secondaryColor = new Color(69, 123, 219); // Azul secundario

        this.getContentPane().setBackground(backgroundColor);
        enviarButton.setBackground(primaryColor);
        omitirButton.setBackground(secondaryColor);
        enviarButton.setForeground(Color.WHITE);
        omitirButton.setForeground(Color.WHITE);
        enviarButton.setBorderPainted(false);
        omitirButton.setBorderPainted(false);
        enviarButton.setFocusPainted(false);
        omitirButton.setFocusPainted(false);

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
        generarRecomendacion(true);
    }

    private void generarRecomendacion(boolean resetIndex) {
        String emocion = (String) selectorEmocion.getSelectedItem();
        String objetivo = (String) selectorObjetivo.getSelectedItem();
        String genero = (String) selectorGenero.getSelectedItem();
        String key = emocion + "_" + objetivo + "_" + genero;

        if (resetIndex) {
            playlistIndex.put(key, 0);
        }

        int index = playlistIndex.getOrDefault(key, 0);
        ArrayList<String> urls = playlists.getOrDefault(key, new ArrayList<String>() {{
            add("https://open.spotify.com/playlist/0cc8YMQWsSzODyTpdVB6mI"); // URL por defecto si no encuentra combinación
        }});

        if (index < urls.size()) {
            String urlRecomendacion = urls.get(index);
            abrirPlaylist(urlRecomendacion);

            String textoRecomendacion = "Abriendo playlist en Spotify: " + urlRecomendacion;
            recomendacionArea.setText(textoRecomendacion);

            playlistIndex.put(key, index + 1);
        } else {
            recomendacionArea.setText("Lo sentimos, no hay más playlists disponibles para esta combinación.");
        }
    }

    private void omitirYRecomendar() {
        generarRecomendacion(false);
    }

    private void abrirPlaylist(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DjEmocional());
    }
}
