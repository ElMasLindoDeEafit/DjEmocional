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
    private JLabel textoInicio, textoObjetivo, textoGenero;  // RE1, RE2, RE3
    private JComboBox<String> selectorEmocion, selectorObjetivo, selectorGenero;  // RE1, RE2, RE3
    private JButton enviarButton, omitirButton;  // RE4
    private JTextArea recomendacionArea;  // RS1
    private JSlider calificacionSlider;  // RE6
    private JLabel calificacionLabel;  // RE6
    private JButton vacacionesButton;
    private JButton meditacionButton;
    private JButton suenoButton;

    // Mapa para almacenar las playlists según género y emoción
    private HashMap<String, ArrayList<String>> playlists;  // RP1, RP4, RP5

    // Mapa para almacenar las calificaciones de playlists
    private HashMap<String, Integer> calificacionesPlaylists;

    // Objeto para generar números aleatorios
    private Random rand;

    // Variable para rastrear la última categoría especial de playlist recomendada
    private String ultimaCategoriaEspecial;

    // Constructor principal de la clase DjEmocional
    public DjEmocional() {
        super("Dj Emocional");  // Título de la ventana

        // Inicializando objeto Random
        rand = new Random();

        // URLs de playlists reales de Spotify acorde a emociones y géneros.
        // La estructura es del tipo genero_emocion
        playlists = new HashMap<>();  // RP1, RP4, RP5

        // Para guardar las calificaciones
        calificacionesPlaylists = new HashMap<>();

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

        // Playlists de eventos especiales
        playlists.put("Vacaciones", new ArrayList<String>() {{
            add("https://open.spotify.com/playlist/2YHiMhMYB1uuNqNma0WtOG?si=c231e00546144230");
            add("https://open.spotify.com/playlist/5wTDil8BISfKKF5FyUSi0j?si=44b06006f61b47c2");
            add("https://open.spotify.com/playlist/5GAzwf2tqhUMAqSpk5CPTU?si=ed9a38fc192c45a8");
            add("https://open.spotify.com/playlist/3U55DTJnPB3GqTz5n06j8O?si=d56d38c0fa434c2f");
            add("https://open.spotify.com/playlist/4pWb4E5DcCek1ptlbP2tRs?si=0f4d5fb33395480a");
        }});
        playlists.put("Meditación", new ArrayList<String>() {{
            add("https://open.spotify.com/playlist/3Fb10PgrQMDz75cCjQO58i?si=7399e57219f44bb7");
            add("https://open.spotify.com/playlist/2OueUHsn6eHPeKdGn33e0P?si=c59335785fb6493f");
            add("https://open.spotify.com/playlist/5OXaOTti5im11mDpPhf1yt?si=009105b59c4942ad");
            add("https://open.spotify.com/playlist/2kMwKQV2ogB1zcI3SFmiDS?si=637c28d1d80f4bfa");
            add("https://open.spotify.com/playlist/2h9lX8opxsxfW5kdNtfOIv?si=86d3b8f53c35456c");
        }});
        playlists.put("Sueño", new ArrayList<String>() {{
            add("https://open.spotify.com/playlist/1cfB5v1OPE9a5k4kPDQvUq?si=f81ed3028b1e4b0e");
            add("https://open.spotify.com/playlist/2olIYJkhTct1n42Aw3TYdM?si=13e32c1b72b24b6c");
            add("https://open.spotify.com/playlist/2mTcDAElAtMbbOFOxEk22E?si=99bd70a6ddfc4437");
            add("https://open.spotify.com/playlist/0Qk5kQp81u5aydRQW8wsvG?si=d6721359e5ac427b");
            add("https://open.spotify.com/playlist/6vr44l8JPLCbfQ1J4H4q7L?si=427198f8acaf4b8c");
        }});



        // Listas desplegables para las emociones, objetivos y géneros
        String[] emociones = {"Enojo", "Felicidad", "Tristeza", "Tranquilidad"};  // RE1
        String[] objetivos = {"Intensificar", "Mantener", "Cambiar"};  // RE2
        String[] generos = {"Rock", "Pop", "Electrónica", "Reggaeton"};  // RE3

        // Inicialización de componentes gráficos y configuraciones visuales
        textoInicio = new JLabel("¿Qué emoción sientes?");
        textoObjetivo = new JLabel("¿Qué quieres hacer con tu emoción?");
        textoGenero = new JLabel("Selecciona tu género musical favorito");
        selectorEmocion = new JComboBox<>(emociones);
        selectorObjetivo = new JComboBox<>(objetivos);
        selectorGenero = new JComboBox<>(generos);
        enviarButton = new JButton("Enviar");
        vacacionesButton = new JButton("Vacaciones");
        meditacionButton = new JButton("Meditación");
        suenoButton = new JButton("Sueño");
        omitirButton = new JButton("Omitir Playlist");
        recomendacionArea = new JTextArea(5, 30);
        recomendacionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(recomendacionArea);
        calificacionLabel = new JLabel("Califica la playlist (1-10):");
        calificacionSlider = new JSlider(1, 10, 5);;
        calificacionSlider.setMajorTickSpacing(1);
        calificacionSlider.setPaintTicks(true);
        calificacionSlider.setPaintLabels(true);

        // Evento al presionar el botón 'Enviar'
        enviarButton.addActionListener(this);  // RS1

        // Evento al presionar el botón 'Omitir'
        omitirButton.addActionListener(e -> omitirYRecomendar());  // RE4, RS2

        //Evento al presionar algun boton de playlists especiales
        vacacionesButton.addActionListener(e -> recomendarPlaylistEspecial("Vacaciones"));
        meditacionButton.addActionListener(e -> recomendarPlaylistEspecial("Meditación"));
        suenoButton.addActionListener(e -> recomendarPlaylistEspecial("Sueño"));

        // Listener para el slider de calificación
        calificacionSlider.addChangeListener(e -> {
            if (!calificacionSlider.getValueIsAdjusting()) {
                String urlActual = recomendacionArea.getText().replace("Abriendo playlist en Spotify: ", "");
                calificacionesPlaylists.put(urlActual, calificacionSlider.getValue());
            }
        });

        // Diseño y estructura de la ventana
        setLayout(new BorderLayout()); // Usar BorderLayout para mejor disposición de componentes
        // Panel central para albergar todos los controles
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Subpaneles para una mejor organización y alineación de los componentes
        JPanel emotionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel objectivePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel genrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel recommendationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel ratingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Añadir componentes a los subpaneles
        emotionPanel.add(textoInicio);
        emotionPanel.add(selectorEmocion);
        objectivePanel.add(textoObjetivo);
        objectivePanel.add(selectorObjetivo);
        genrePanel.add(textoGenero);
        genrePanel.add(selectorGenero);
        buttonPanel.add(enviarButton);
        buttonPanel.add(omitirButton);
        recommendationPanel.add(new JScrollPane(recomendacionArea));
        ratingPanel.add(calificacionLabel);
        ratingPanel.add(calificacionSlider);

        // Añadir subpaneles al panel central
        centerPanel.add(emotionPanel);
        centerPanel.add(objectivePanel);
        centerPanel.add(genrePanel);
        centerPanel.add(buttonPanel);
        centerPanel.add(recommendationPanel);
        centerPanel.add(ratingPanel);

        // Añadir panel central al JFrame
        add(centerPanel, BorderLayout.CENTER);

        // Panel inferior para los botones de playlist especiales
        JPanel specialButtonPanel = new JPanel();
        specialButtonPanel.setLayout(new BoxLayout(specialButtonPanel, BoxLayout.X_AXIS));
        specialButtonPanel.add(vacacionesButton);
        specialButtonPanel.add(meditacionButton);
        specialButtonPanel.add(suenoButton);
        add(specialButtonPanel, BorderLayout.SOUTH);

        // Estilización de componentes para un look más moderno
        this.getContentPane().setBackground(new Color(185, 209, 248, 195));
        enviarButton.setBackground(new Color(117, 162, 255));
        omitirButton.setBackground(new Color(117, 162, 255));;
        enviarButton.setForeground(Color.WHITE);
        omitirButton.setForeground(Color.WHITE);
        enviarButton.setBorderPainted(false);
        omitirButton.setBorderPainted(false);
        enviarButton.setFocusPainted(false);
        omitirButton.setFocusPainted(false);


        // Configuraciones finales de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Ajustar el tamaño de la ventana al contenido
        setLocationRelativeTo(null); // Centrar ventana en la pantalla
        setResizable(false); // Evitar que la ventana cambie de tamaño
        setVisible(true);

    }


    // Método que se ejecuta al hacer clic en el botón 'Enviar'
    @Override
    public void actionPerformed(ActionEvent e) {
        generarRecomendacion();  // RS1
    }

    // Método para recomendar una playlist especial
    private void recomendarPlaylistEspecial(String evento) {
        ArrayList<String> urls = playlists.getOrDefault(evento, new ArrayList<>());
        int randomIndex = rand.nextInt(urls.size());
        String urlRecomendacion = urls.get(randomIndex);
        abrirPlaylist(urlRecomendacion);
        recomendacionArea.setText("Abriendo playlist en Spotify: " + urlRecomendacion);
        ultimaCategoriaEspecial = evento; // Actualiza la última categoría especial recomendada
    }

    // Método para generar una recomendación de playlist
    private void generarRecomendacion() {
        // Se obtienen los valores seleccionados en las listas desplegables
        String emocion = (String) selectorEmocion.getSelectedItem();  // RE1
        String objetivo = (String) selectorObjetivo.getSelectedItem();  // RE2
        String genero = (String) selectorGenero.getSelectedItem();  // RE3

        // Se forma una clave para buscar en el mapa de playlists
        String key = genero + "_" + emocion;  // RP1

        // Si el objetivo es 'Cambiar', se obtiene una emoción opuesta
        if (objetivo.equals("Cambiar")) {  // RP1
            key = genero + "_" + getEmocionOpuesta(emocion);
        }

        // Se obtiene una lista de URLs según la clave generada
        ArrayList<String> urls = playlists.getOrDefault(key, new ArrayList<>());  // RP1

        // Se selecciona una URL de manera aleatoria de la lista obtenida
        int randomIndex = rand.nextInt(urls.size());
        String urlRecomendacion = urls.get(randomIndex);  // RP1

        // Se abre la URL seleccionada en el navegador predeterminado
        abrirPlaylist(urlRecomendacion);  // RS1

        // Se muestra en la interfaz la URL recomendada
        String textoRecomendacion = "Abriendo playlist en Spotify: " + urlRecomendacion;  // RS1
        recomendacionArea.setText(textoRecomendacion);

        ultimaCategoriaEspecial = null; // Restablece la última categoría especial cuando se genera una recomendación regular

    }

    // Método para omitir y generar una nueva recomendación
    private void omitirYRecomendar() {
        if (ultimaCategoriaEspecial != null) {
            // Si la última recomendación fue una playlist especial, recomendamos otra de la misma categoría
            recomendarPlaylistEspecial(ultimaCategoriaEspecial);
        } else {
            // Si no, generamos una recomendación regular
            generarRecomendacion();
        }
    }

    // Método para obtener una emoción opuesta a la seleccionada
    private String getEmocionOpuesta(String emocion) {  // RP1
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
            Desktop.getDesktop().browse(new URI(url));  // RS1
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método principal para ejecutar el programa
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DjEmocional());
    }
}
