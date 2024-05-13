package org.example.repository;

import org.example.entity.Person;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//9.3
public class DirectoryPersonRepository implements Repository<Person> {
    private final File dir;
    public DirectoryPersonRepository(File dir) {
        this.dir = dir;
        //проверяем является ли папкой
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("This is not a directory");
        }
        //проверяем существует ли она
        if (!dir.exists()) {
            //если нет - создаем
            dir.mkdirs();
        }
    }


    @Override
    public void save(Person person) throws IOException {
        File file = new File(dir.getPath() + person.getId());
        Person.saveTo(file, person);
    }
    //9.4
    public List<Person> load (List<Integer> ids) throws IOException {
        List<Person> loadedElements = new ArrayList<>();
        for (Integer id : ids) {
            File file = new File(dir.getPath() + id);
            if (file.exists()) {
                Person person = Person.loadFrom(file);
                loadedElements.add(person);
            }
        }
        return loadedElements;
    }
    @Override
    public Person load(int id) throws IOException {
        File file = new File(dir.getPath() + id);
        return Person.loadFrom(file);
    }

    @Override
    public List<Person> loadAll() throws IOException {
        File [] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        }
        List<Person> listPerson = new ArrayList<>();
        for (File file : files) {
            listPerson.add(Person.loadFrom(file));
        }
        return listPerson;
    }

    public File getDir() {
        return dir;
    }
}
