package org.arolla.nexity.infrastructure.controllers.integrationTests;

import com.google.gson.Gson;
import org.arolla.nexity.infrastructure.controllers.AnnoncesController;
import org.arolla.nexity.infrastructure.models.AdresseModel;
import org.arolla.nexity.infrastructure.models.AnnonceCreationModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnoncesControllerIntegrationTest {

	MockMvc mockMvc;

	@Autowired
	AnnoncesController annoncesController;

	@Before
	public void setup() {

		this.mockMvc = standaloneSetup(this.annoncesController).build();
	}

	@Test
	public void canCreateAnnonceOnController() throws Exception {
		ResultActions perform = createAnnonce();
		perform.andExpect(status().isOk());
	}

	private ResultActions createAnnonce() throws Exception {
		return createAnnonceWithVille("test");
	}

	private ResultActions createAnnonceWithVille(String ville) throws Exception {
		AnnonceCreationModel annonceCreationModel = new AnnonceCreationModel("mon Titre", "mon Descriptif",
				new AdresseModel("mon Adresse", "12345", ville), 100D, 100000D);
		Gson gson = new Gson();
		String json = gson.toJson(annonceCreationModel, AnnonceCreationModel.class);
		return mockMvc.perform(put("/annonces/").contentType(MediaType.APPLICATION_JSON_VALUE).content(json));
	}

	@Test
	public void canCreateAnnonceAndGetIt() throws Exception {
		ResultActions annonce = createAnnonce();
		MvcResult mvcResult = annonce.andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		String annonceId = response.getContentAsString();
		MvcResult getAnnonce = mockMvc.perform(get("/annonces/" + annonceId)).andReturn();
		MockHttpServletResponse getAnnonceResponse = getAnnonce.getResponse();
		String contentAsString = getAnnonceResponse.getContentAsString();

		assertThat(contentAsString).contains(annonceId);
	}

	@Test
	public void canCreateManyAnnoncesAndGetThem() throws Exception {

		MvcResult autreVille = createAnnonceWithVille("autreVille").andReturn();
		for (int i = 0; i<30;i++) {
			createAnnonceWithVille("maVille").andReturn();
		}
		mockMvc.perform(get("/annonces/ville/maVille?offset=12&numberOfResults=5").contentType(MediaType.APPLICATION_JSON_VALUE));
	}
}
