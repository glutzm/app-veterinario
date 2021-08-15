package br.com.lutztechnology.appveterinario.serialize;

import br.com.lutztechnology.appveterinario.model.Animal;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class AnimalSerializer extends StdSerializer<Animal> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AnimalSerializer() {
        this(null);
    }

    protected AnimalSerializer(Class<Animal> t) {
        super(t);
    }

    @Override
    public void serialize(
            Animal animal,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", animal.getId());
        jsonGenerator.writeStringField("name", animal.getName());
        jsonGenerator.writeStringField("age", formatter.format(animal.getAge()));
        jsonGenerator.writeNumberField("ageYears", animal.getAgeYears());
        jsonGenerator.writeStringField("breed", animal.getBreed());
        jsonGenerator.writeObjectFieldStart("owner");
        jsonGenerator.writeNumberField("id", animal.getOwner().getId());
        jsonGenerator.writeStringField("name", animal.getOwner().getName());
        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();
    }
}