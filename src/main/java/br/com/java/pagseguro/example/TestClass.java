package br.com.java.pagseguro.example;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class TestClass {
	
	public static void main(String[] args) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance("br.com.java.pagseguro.example");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<Carro> element = (JAXBElement<Carro>) unmarshaller.unmarshal(new File("src/main/resources/carros.xml"));
		Carro carro = element.getValue();
		List<Motorista> motorista = carro.getMotoristas();
		
		System.out.println(carro.getNome());
		System.out.println(carro.getPortas());
		
		motorista.forEach(m -> {
			System.out.println(m.getNome());
		});
	}

}
