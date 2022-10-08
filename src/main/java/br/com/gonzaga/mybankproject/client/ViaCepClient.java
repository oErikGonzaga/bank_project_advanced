package br.com.gonzaga.mybankproject.client;

import br.com.gonzaga.mybankproject.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br")
public interface ViaCepClient {

    @GetMapping("ws/{cep}/json/")
    AddressResponse getAddressByCep(@PathVariable("cep") String cep);
}
