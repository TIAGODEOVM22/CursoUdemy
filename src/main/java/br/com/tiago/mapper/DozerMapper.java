package br.com.tiago.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;


public class DozerMapper {
	
	
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	/*FAZ A CONVERSÃO DE DOMAIN PARA V.O
	 * tendo dois tipos O, D*/
	public static <O, D> D parseObject (O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	
	/*FAZ A CONVERSÃO DE LISTA DOMAIN PARA V.O*/
	public static <O, D> List<D> parseListObjects (List<O> origin, Class<D> destination) {
		List<D> destinationObjects = new ArrayList<D>();
		
		for (O o : origin) {
			destinationObjects.add(mapper.map(o, destination));
			
		}
		return destinationObjects;
	}
	

}
