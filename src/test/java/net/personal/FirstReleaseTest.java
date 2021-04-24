package mx.com.telcel.di.sds.gsa.dssc.sicatel.sicatelvtcp;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class FirstReleaseTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private final String URL_SERVICE = "http://localhost:8787/tarjetas/api/v1/soap-notification";

	
	@Test
	void folioYacontabilizado() throws Exception {
		ResultActions response = this.mockMvc.perform(post(URL_SERVICE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_FOLIO_CONTABILIZADO));
		verifiAsserts(response);
	}
	
	@Test
	void materialNoActivo() throws Exception {
		ResultActions response = this.mockMvc.perform(post(URL_SERVICE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(MATERIAL_NO_ACTIVO));
		verifiAsserts(response);
	}
	
	@Test
	void almacenVacio() throws Exception {
		ResultActions response = this.mockMvc.perform(post(URL_SERVICE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(ALMACEN_VACIO));
		response.andExpect(content().string(containsString("Almacen requerido")));
		verifiAsserts(response);
	}
	
	@Test
	void formatoFechaIncorrecto() throws Exception {
		ResultActions response = this.mockMvc.perform(post(URL_SERVICE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(FORMATO_FECHA_INCORRECTO));
		response.andExpect(content().string(containsString("Formato de fecha incorrecto")));
		verifiAsserts(response);
	}
	
	@Test
	void jsonInvalido() throws Exception {
		this.mockMvc.perform(post(URL_SERVICE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSON_INVALIDO))
				.andExpect(status().isInternalServerError())
				.andExpect(content().string(containsString("0000")))
				.andExpect(content().string(containsString("INTERNAL_SERVER_ERROR")));
	}
	
	@Test
	void solicitudVentaCorrecta() throws Exception {
		ResultActions response = this.mockMvc.perform(post(URL_SERVICE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(SOLICITUD_VENTA_CORRECTA));
		verifiAssertsStatusOk(response);
	}
	
	@Test
	void solicitudCancelacionCorrecta() throws Exception {
		ResultActions response = this.mockMvc.perform(post(URL_SERVICE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(SOLICITUD_CANCELACION_CORRECTA));
				//.andDo(print());
		verifiAssertsStatusOk(response);
	}
	
	
	private void verifiAsserts(ResultActions verifyResponse) throws Exception {
		verifyResponse.andExpect(status().isBadRequest())
		.andExpect(content().string(containsString("0000")))
		.andExpect(content().string(containsString("BAD_REQUEST")))
		.andExpect(jsonPath("$.reintentos", is(0), Integer.class))
		.andExpect(jsonPath("$.matDoc", is("")));
	}
	
	private void verifiAssertsStatusOk(ResultActions verifyResponse) throws Exception {
		verifyResponse.andExpect(status().isOk())
		.andExpect(content().string(containsString("2021")))
		.andExpect(content().string(containsString("OK")))
		.andExpect(jsonPath("$.reintentos", is(0), Integer.class))
		.andExpect(jsonPath("$.matDoc").isNotEmpty())
		.andExpect(jsonPath("$.errorMensaje", is("")));
	}


	private final String REQUEST_FOLIO_CONTABILIZADO = "{\r\n"
			+ "  \"almacen\": \"C006\",\r\n"
			+ "  \"cantidad\": 1,\r\n"
			+ "  \"centro\": \"MX09\",\r\n"
			+ "  \"fecha\": \"16/03/2021\",\r\n"
			+ "  \"folioSicatel\": \"9287349875\",\r\n"
			+ "  \"material\": \"7006231\",\r\n"
			+ "  \"tiSeries\": [\r\n"
			+ "    {\r\n"
			+ "      \"lote\": \"31122024\",\r\n"
			+ "      \"serie\": \"4152313114140002\"\r\n"
			+ "    }\r\n"
			+ "  ],\r\n"
			+ "  \"tipoMovimiento\": \"V\",\r\n"
			+ "  \"uom\": \"PZA\"\r\n"
			+ "}";
	
	private final String MATERIAL_NO_ACTIVO = "{\r\n"
			+ "  \"almacen\": \"C000\",\r\n"
			+ "  \"cantidad\": 1,\r\n"
			+ "  \"centro\": \"MX09\",\r\n"
			+ "  \"fecha\": \"16/03/2021\",\r\n"
			+ "  \"folioSicatel\": \"9287349875\",\r\n"
			+ "  \"material\": \"7006231\",\r\n"
			+ "  \"tiSeries\": [\r\n"
			+ "    {\r\n"
			+ "      \"lote\": \"31122024\",\r\n"
			+ "      \"serie\": \"4152313114140002\"\r\n"
			+ "    }\r\n"
			+ "  ],\r\n"
			+ "  \"tipoMovimiento\": \"V\",\r\n"
			+ "  \"uom\": \"PZA\"\r\n"
			+ "}";
	
	private final String ALMACEN_VACIO = "{\r\n"
			+ "  \"almacen\": \"\",\r\n"
			+ "  \"cantidad\": 1,\r\n"
			+ "  \"centro\": \"MX09\",\r\n"
			+ "  \"fecha\": \"16/03/2021\",\r\n"
			+ "  \"folioSicatel\": \"9287349875\",\r\n"
			+ "  \"material\": \"7006231\",\r\n"
			+ "  \"tiSeries\": [\r\n"
			+ "    {\r\n"
			+ "      \"lote\": \"31122024\",\r\n"
			+ "      \"serie\": \"4152313114140002\"\r\n"
			+ "    }\r\n"
			+ "  ],\r\n"
			+ "  \"tipoMovimiento\": \"V\",\r\n"
			+ "  \"uom\": \"PZA\"\r\n"
			+ "}";
	
	private final String FORMATO_FECHA_INCORRECTO = "{\r\n"
			+ "  \"almacen\": \"C000\",\r\n"
			+ "  \"cantidad\": 1,\r\n"
			+ "  \"centro\": \"MX09\",\r\n"
			+ "  \"fecha\": \"16-03-2021\",\r\n"
			+ "  \"folioSicatel\": \"9287349875\",\r\n"
			+ "  \"material\": \"7006231\",\r\n"
			+ "  \"tiSeries\": [\r\n"
			+ "    {\r\n"
			+ "      \"lote\": \"31122024\",\r\n"
			+ "      \"serie\": \"4152313114140002\"\r\n"
			+ "    }\r\n"
			+ "  ],\r\n"
			+ "  \"tipoMovimiento\": \"V\",\r\n"
			+ "  \"uom\": \"PZA\"\r\n"
			+ "}";
	
	private final String JSON_INVALIDO = "{\r\n"
			+ "  \"almacen\": \"C000\"\r\n"
			+ "  \"cantidad\": 1,\r\n"
			+ "  \"centro\": \"MX09\",\r\n"
			+ "  \"fecha\": \"16/03/2021\",\r\n"
			+ "  \"folioSicatel\": \"9287349875\",\r\n"
			+ "  \"material\": \"7006231\",\r\n"
			+ "  \"tiSeries\": [\r\n"
			+ "    {\r\n"
			+ "      \"lote\": \"31122024\",\r\n"
			+ "      \"serie\": \"4152313114140002\"\r\n"
			+ "    }\r\n"
			+ "  ],\r\n"
			+ "  \"tipoMovimiento\": \"V\",\r\n"
			+ "  \"uom\": \"PZA\"\r\n"
			+ "}";
	
	private final String SOLICITUD_VENTA_CORRECTA = "{\r\n"
			+ "  \"almacen\": \"C006\",\r\n"
			+ "  \"cantidad\": 1,\r\n"
			+ "  \"centro\": \"MX09\",\r\n"
			+ "  \"fecha\": \"20/04/2021\",\r\n"
			+ "  \"folioSicatel\": \"9787349884\",\r\n"
			+ "  \"material\": \"7006231\",\r\n"
			+ "  \"tiSeries\": [\r\n"
			+ "    {\r\n"
			+ "      \"lote\": \"31122024\",\r\n"
			+ "      \"serie\": \"4152313114140006\"\r\n"
			+ "    }\r\n"
			+ "  ],\r\n"
			+ "  \"tipoMovimiento\": \"V\",\r\n"
			+ "  \"uom\": \"PZA\"\r\n"
			+ "}";
	
	private final String SOLICITUD_CANCELACION_CORRECTA = "{\r\n"
			+ "  \"almacen\": \"C006\",\r\n"
			+ "  \"cantidad\": 1,\r\n"
			+ "  \"centro\": \"MX09\",\r\n"
			+ "  \"fecha\": \"20/04/2021\",\r\n"
			+ "  \"folioSicatel\": \"9787349883\",\r\n"
			+ "  \"material\": \"7006231\",\r\n"
			+ "  \"tiSeries\": [\r\n"
			+ "    {\r\n"
			+ "      \"lote\": \"31122024\",\r\n"
			+ "      \"serie\": \"4152313114140005\"\r\n"
			+ "    }\r\n"
			+ "  ],\r\n"
			+ "  \"tipoMovimiento\": \"C\",\r\n"
			+ "  \"uom\": \"PZA\"\r\n"
			+ "}";
}
