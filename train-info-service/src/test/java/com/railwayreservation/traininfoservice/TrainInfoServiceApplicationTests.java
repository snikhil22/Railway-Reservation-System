package com.railwayreservation.traininfoservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.railwayreservation.traininfoservice.model.TrainModel;
import com.railwayreservation.traininfoservice.repository.TrainRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class TrainInfoServiceApplicationTests {

	@MockBean
	private TrainRepository trainRepository;

	@Autowired
	private TrainInfoController trainInfoController;


	@Test
	public void addTrainTest() {
		TrainModel train = new TrainModel(54, "NandaDeviExpress", "Dehradun", "Delhi", 10, 20, 100.00, 150.00);
		when(trainRepository.save(train)).thenReturn(train);
		assertEquals("Train added successfully!!!", trainInfoController.addTrain(train));
	}

	@Test
	public void searchTrainsTest() {
		String from="Dehradun";
		String to="Delhi";
		when(trainRepository.findTrainByStations(from, to)).thenReturn(
				Stream.of(new TrainModel(54, "NandaDeviExpress", "Dehradun", "Delhi", 10, 20, 100.00, 150.00))
						.collect(Collectors.toList()));

		List<TrainModel> list = trainRepository.findTrainByStations(from,to);

		assertEquals(54, list.get(0).getTrainId());
		assertEquals("NandaDeviExpress", list.get(0).getTrainName());
		assertEquals("Dehradun", list.get(0).getFromStation());
		assertEquals("Delhi", list.get(0).getToStation());
		assertEquals(10, list.get(0).getTotalSeatsLadies());
		assertEquals(20, list.get(0).getTotalSeatsGeneral());
		assertEquals(100.00, list.get(0).getFareLadies());
		assertEquals(150.00, list.get(0).getFareGeneral());

	}

	@Test
	public void updateTrainFareTest() {
		TrainModel train = new TrainModel(54, "NandaDeviExpress", "Dehradun", "Delhi", 10, 20, 100.00, 150.00);
		when(trainRepository.findTrainByNameAndStations("NandaDeviExpress","Dehradun","Delhi")).thenReturn(new TrainModel(54, "NandaDeviExpress", "Dehradun", "Delhi", 10, 20, 120.00, 170.00));
		train = trainRepository.findTrainByNameAndStations("NandaDeviExpress","Dehradun","Delhi");
		
		assertEquals(54, train.getTrainId());
		assertEquals("NandaDeviExpress",train.getTrainName());
		assertEquals("Dehradun", train.getFromStation());
		assertEquals("Delhi", train.getToStation());
		assertEquals(10, train.getTotalSeatsLadies());
		assertEquals(20, train.getTotalSeatsGeneral());
		assertEquals(120.00, train.getFareLadies());
		assertEquals(170.00,train.getFareGeneral());
	}

	@Test
	public void deleteTrainTest() {

		trainRepository.deleteAllTrainsByName("NandaDeviExpress");
		verify(trainRepository, times(1)).deleteAllTrainsByName("NandaDeviExpress");
	}
}