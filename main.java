import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class EmotionalMusicAssistant extends JFrame implements ActionListener {
    private JLabel textoInicio, textoObjetivo, textoGenero, textoTiempo;
    private JComboBox<String> selectorEmocion, selectorObjetivo, selectorGenero;
    private JButton enviarButton, omitirButton;
    private JTextArea recomendacionArea;
    private JSpinner spinnerTiempo;
    private JSlider calificacionSlider;
    private JLabel calificacionLabel;


    public EmotionalMusicAssistant() {
        super("Emotional Music Assistant");


        // Preparación de componentes
        String[] emociones = {"Felicidad", "Tristeza", "Enojo", "Ansiedad", "Estrés"};
        String[] objetivos = {"Intensificar", "Mantener", "Cambiar"};
        String[] generos = {"Rock", "Jazz", "Pop", "Electrónica", "Clásica"};


        // Creación de componentes
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
        
        textoTiempo = new JLabel("¿Cuántos minutos quieres escuchar?");
        spinnerTiempo = new JSpinner(new SpinnerNumberModel(30, 1, 240, 1));


        calificacionLabel = new JLabel("Califica la playlist (1-10):");
        calificacionSlider = new JSlider(1, 10, 5);
        calificacionSlider.setMajorTickSpacing(1);
        calificacionSlider.setPaintTicks(true);
        calificacionSlider.setPaintLabels(true);


        // Registro de eventos
        enviarButton.addActionListener(this);
        omitirButton.addActionListener(e -> recomendacionArea.setText("¡Okay! Generando otra recomendación...")); // Simplificado


        // Configuración del panel y adición de componentes
        setLayout(new FlowLayout());
        add(textoInicio);
        add(selectorEmocion);
        add(textoObjetivo);
        add(selectorObjetivo);
        add(textoGenero);
        add(selectorGenero);
        add(textoTiempo);
        add(spinnerTiempo);
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
        String emocion = (String) selectorEmocion.getSelectedItem();
        String objetivo = (String) selectorObjetivo.getSelectedItem();
        String genero = (String) selectorGenero.getSelectedItem();
        int minutos = (int) spinnerTiempo.getValue();


        String recomendacion = "Basado en tu emoción " + emocion + ", tu deseo de " + objetivo + ", tu preferencia por "
                             + genero + " y por " + minutos + " minutos, te recomendamos la playlist: ..."; 


        recomendacionArea.setText(recomendacion);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmotionalMusicAssistant());
    }
}
