package com.ardic.example.rest.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ardic.example.rest.model.Event;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class EventRepository {

	private final String REPOSITORY_FILE_NAME = "event-repository.txt";

	File file = new File(REPOSITORY_FILE_NAME);

	private List<Event> eventList;
	private final AtomicLong counter = new AtomicLong();

	Logger logger = Logger.getLogger(EventRepository.class);

	public EventRepository() throws IOException {
		if (file.exists()) {
			InputStream is = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String eventString = br.readLine();
			br.close();

			eventList = new Gson().fromJson(eventString, new TypeToken<List<Event>>() {
			}.getType());
			counter.set(eventList.get(eventList.size() - 1).getId());
		} else {
			file.createNewFile();
			eventList = new ArrayList<Event>();
			try (FileWriter writer = new FileWriter(file.toString())) {
				writer.write(new Gson().toJson(eventList));
			} catch (IOException e) {
				logger.error("Cannot create file " + REPOSITORY_FILE_NAME);
			}

		}
	}

	public void addNewEvent(String event) {
		eventList.add(new Event(counter.incrementAndGet(), event, new Date()));

		try (FileWriter writer = new FileWriter(file.toString())) {
			writer.write(new Gson().toJson(eventList));
		} catch (IOException e) {
			logger.error("Cannot write to file " + REPOSITORY_FILE_NAME);
		}
	}

	public ArrayList<Event> getEventList() {
		return (ArrayList<Event>) eventList;
	}
}
