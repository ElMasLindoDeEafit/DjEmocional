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
    // Definición de componentes gráficos para la interfaz
    private JLabel textoInicio, textoObjetivo, textoGenero;
    private JComboBox<String> selectorEmocion, selectorObjetivo, selectorGenero;
    private JButton enviarButton, omitirButton;
    private JTextArea recomendacionArea;
    private JSlider calificacionSlider;
    private JLabel calificacionLabel;
    
    // Mapa para almacenar las playlists según género y emoción
    private HashMap<String, ArrayList<String>> playlists;
    
    // Objeto para generar números aleatorios
    private Random rand;

    // Constructor principal de la clase DjEmocional
    public DjEmocional() {
        super("Dj Emocional");  // Título de la ventana

        // Inicializando objeto Random
        rand = new Random();

        // URLs de playlists reales de Spotify acorde a emociones y géneros.
        // La estructura es del tipo genero_emocion
        playlists = new HashMap<>();

        // Playlists para Rock, Pop, Electrónica y Reggaeton según emociones

        // Playlists Rock
        playlists.put("Rock_Enojo", new ArrayList<String>() {{
            add("https://spotify.link/yuvzfJZPzDb");
            add("https://spotify.link/sLlKtR2PzDb");
            add("https://spotify.link/kR98lP9PzDb");
        }});
        playlists.put("Rock_Felicidad", new ArrayList<String>() {{
            add("https://spotify.link/KNqYizdQzDb");
            add("https://spotify.link/GzbhDyhQzDb");
            add("https://spotify.link/Szk12KiQzDb");
        }});
        playlists.put("Rock_Tristeza", new ArrayList<String>() {{
            add("https://spotify.link/vKS02dmQzDb");
            add("https://spotify.link/EUhxqLnQzDb");
            add("https://spotify.link/bcHgQnpQzDb");
        }});
        playlists.put("Rock_Tranquilidad", new ArrayList<String>() {{
            add("https://spotify.link/nt8tsZsQzDb");
            add("https://spotify.link/BnHxGSuQzDb");
            add("https://spotify.link/vEPnoYwQzDb");
        }});
        
        // Playlists Pop
        playlists.put("Pop_Enojo", new ArrayList<String>() {{
            add("https://spotify.link/Y2SSuilRzDb");
            add("https://spotify.link/QSRIk7mRzDb");
            add("https://spotify.link/nOTC5mpRzDb");
        }});
        playlists.put("Pop_Felicidad", new ArrayList<String>() {{
            add("https://spotify.link/LxSRdqsRzDb");
            add("https://spotify.link/KHl6j6tRzDb");
            add("https://spotify.link/OopqwsvRzDb");
        }});
        playlists.put("Pop_Tristeza", new ArrayList<String>() {{
            add("https://spotify.link/KD5Y8PxRzDb");
            add("https://spotify.link/ZHWJctzRzDb");
            add("https://spotify.link/GlrL9OARzDb");
        }});
        playlists.put("Pop_Tranquilidad", new ArrayList<String>() {{
            add("https://spotify.link/1Hir8mCRzDb");
            add("https://spotify.link/H0bBKVDRzDb");
            add("https://spotify.link/uGeUgAFRzDb");
        }});
        
        // Playlists Electrónica
        playlists.put("Electrónica_Enojo", new ArrayList<String>() {{
            add("https://spotify.link/mXutF5SSzDb");
            add("https://spotify.link/ZglRCSVSzDb");
            add("https://spotify.link/yP2onlXSzDb");
        }});
        playlists.put("Electrónica_Felicidad", new ArrayList<String>() {{
            add("https://spotify.link/SBEoSaZSzDb");
            add("https://spotify.link/Y4zLhx0SzDb");
            add("https://spotify.link/FbR0am2SzDb");
        }});
        playlists.put("Electrónica_Tristeza", new ArrayList<String>() {{
            add("https://spotify.link/sqpW4V3SzDb");
            add("https://spotify.link/evNaVj5SzDb");
            add("https://spotify.link/nSPVoE8SzDb");
        }});
        playlists.put("Electrónica_Tranquilidad", new ArrayList<String>() {{
            add("https://spotify.link/zlkg2saTzDb");
            add("https://spotify.link/LWbrFIdTzDb");
            add("https://spotify.link/H31iAhfTzDb");
        }});
        
        // Playlists Reggaeton
        playlists.put("Reggaeton_Enojo", new ArrayList<String>() {{
            add("https://spotify.link/lBuDKEhXzDb");
            add("https://spotify.link/Y7tlXgjXzDb");
            add("https://spotify.link/yLHOhZkXzDb");
        }});
        playlists.put("Reggaeton_Felicidad", new ArrayList<String>() {{
            add("https://spotify.link/7JbWGsmXzDb");
            add("https://spotify.link/GZIZxeoXzDb");
            add("https://spotify.link/XvNmyFpXzDb");
        }});
        playlists.put("Reggaeton_Tristeza", new ArrayList<String>() {{
            add("https://spotify.link/wAlMZfrXzDb");
            add("https://spotify.link/4sDeQUsXzDb");
            add("https://spotify.link/99trsjuXzDb");
        }});
        playlists.put("Reggaeton_Tranquilidad", new ArrayList<String>() {{
            add("https://spotify.link/CRbBBfwXzDb");
            add("https://spotify.link/bCe6d4xXzDb");
            add("https://spotify.link/10c5wPzXzDb");
        }});

        // Listas desplegables para las emociones, objetivos y géneros
        String[] emociones = {"Enojo", "Felicidad", "Tristeza", "Tranquilidad"};
        String[] objetivos = {"Intensificar", "Mantener", "Cambiar"};
        String[] generos = {"Rock", "Pop", "Electrónica", "Reggaeton"};

        // Inicialización de componentes gráficos y configuraciones visuales
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
        
        // Evento al presionar el botón 'Enviar'
        enviarButton.addActionListener(this);
        
        // Evento al presionar el botón 'Omitir'
        omitirButton.addActionListener(e -> omitirYRecomendar());

        // Diseño y estructura de la ventana
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
    
    // Método que se ejecuta al hacer clic en el botón 'Enviar'
    @Override
    public void actionPerformed(ActionEvent e) {
        generarRecomendacion();
    }

    // Método para generar una recomendación de playlist
    private void generarRecomendacion() {
        // Se obtienen los valores seleccionados en las listas desplegables
        String emocion = (String) selectorEmocion.getSelectedItem();
        String objetivo = (String) selectorObjetivo.getSelectedItem();
        String genero = (String) selectorGenero.getSelectedItem();
        
        // Se forma una clave para buscar en el mapa de playlists
        String key = genero + "_" + emocion;

        // Si el objetivo es 'Cambiar', se obtiene una emoción opuesta
        if (objetivo.equals("Cambiar")) {
            key = genero + "_" + getEmocionOpuesta(emocion);
        }

        // Se obtiene una lista de URLs según la clave generada
        ArrayList<String> urls = playlists.getOrDefault(key, new ArrayList<>());
        
        // Se selecciona una URL de manera aleatoria de la lista obtenida
        int randomIndex = rand.nextInt(urls.size());
        String urlRecomendacion = urls.get(randomIndex);
        
        // Se abre la URL seleccionada en el navegador predeterminado
        abrirPlaylist(urlRecomendacion);

        // Se muestra en la interfaz la URL recomendada
        String textoRecomendacion = "Abriendo playlist en Spotify: " + urlRecomendacion;
        recomendacionArea.setText(textoRecomendacion);
    }

    // Método para obtener una emoción opuesta a la seleccionada
    private String getEmocionOpuesta(String emocion) {
        switch (emocion) {
            case "Enojo":
                return "Tranquilidad";
            case "Felicidad":
                return "Tristeza";
            case "Tristeza":
                return "Felicidad";
            case "Tranquilidad":
                return "Enojo";
            default:
                return "Felicidad";
        }
    }

    // Método para abrir una URL en el navegador predeterminado
    private void abrirPlaylist(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método principal para ejecutar el programa
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DjEmocional());
    }
}
