package org.example;

import org.apache.commons.lang3.StringUtils;
import org.example.model.Admission;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

@SpringBootApplication
public class COMP2005DeskApplication extends JFrame {
    @Resource
    RestTemplate restTemplate;

    public COMP2005DeskApplication() {
        initUI();
    }

    private void initUI() {
        JButton jButton = new JButton("F1");

        JLabel jLabel = new JLabel("Patient ID:");
        JTextField jTextField = new JTextField();
        jTextField.setSize( 200, 30 );

        Container pane = getContentPane();

        GroupLayout layout = new GroupLayout( pane );

        pane.setLayout( layout );
        layout.setAutoCreateContainerGaps( true );
        layout.setAutoCreateGaps( true );

        JTextArea jTextArea = new JTextArea();

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup( layout.createParallelGroup().addComponent( jLabel ) );
        vGroup.addGroup( layout.createParallelGroup().addComponent( jTextField ).addComponent( jButton ).addComponent( jTextArea ) );
        layout.setHorizontalGroup( vGroup );

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        hGroup.addGroup( layout.createParallelGroup( GroupLayout.Alignment.BASELINE).addComponent( jLabel ).addComponent( jTextField ) );
        hGroup.addGroup( layout.createParallelGroup().addComponent( jButton ) );
        hGroup.addGroup( layout.createParallelGroup().addComponent( jTextArea ) );

        layout.setVerticalGroup( hGroup );

        jButton.addActionListener( (ActionEvent event) -> {
            String patientIdStr = jTextField.getText();
            if ( !StringUtils.isNumeric( patientIdStr ) ) {
                JOptionPane.showMessageDialog( null, "please input correct patient id", "warning", JOptionPane.WARNING_MESSAGE );
                jTextArea.setText( "EMPTY" );
                return;
            }
            String requestUrl = String.format( "http://localhost:8080/Patients/%s/admissions", patientIdStr );
            ParameterizedTypeReference<List<Admission>> responseType = new ParameterizedTypeReference<List<Admission>>() {};
            ResponseEntity<List<Admission>> response = restTemplate.exchange( requestUrl, HttpMethod.GET, null, responseType );

            if ( CollectionUtils.isEmpty( response.getBody() ) ) {
                JOptionPane.showMessageDialog( null, "there is no admission for patient " + patientIdStr, "warning", JOptionPane.WARNING_MESSAGE );
                jTextArea.setText( "EMPTY" );
                return;
            }
            StringBuilder admissionStr = new StringBuilder();
            for ( Admission admission : response.getBody() ) {
                admissionStr.append( "id:" + "\t" ).append( admission.getId() ).append( "\n" );
                admissionStr.append( "admissionDate:" + "\t" ).append( admission.getAdmissionDate() ).append( "\n" );
                admissionStr.append( "dischargeDate:" + "\t" ).append( admission.getDischargeDate() ).append( "\n" );
                admissionStr.append( "patientID:" + "\t" ).append( admission.getPatientID() ).append( "\n" );
                admissionStr.append( "\n" );
            }
            jTextArea.setText( admissionStr.toString() );
        } );

        setTitle( "COMP2005" );

        setSize( 600, 600 );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder( COMP2005DeskApplication.class )
                .headless( false ).run( args );

        EventQueue.invokeLater( () -> {
            COMP2005DeskApplication ex = ctx.getBean( COMP2005DeskApplication.class );
            ex.setVisible( true );
        } );
    }
}
