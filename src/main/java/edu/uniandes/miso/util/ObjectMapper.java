package edu.uniandes.miso.util;

import java.util.List;
import java.util.stream.Collectors;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public final class ObjectMapper {

	public static <O, D> D mappingObject(O claseOrigen, Class<D> claseDestino) {
		Mapper mapper = DozerBeanMapperBuilder.buildDefault();
		return mapper.map(claseOrigen, claseDestino);
	}

	public static <O, D> List<D> mapList(List<O> fromList, final Class<D> toClass) {
		return fromList.stream().map(from -> mappingObject(from,toClass)).collect(Collectors.toList());
	}
	private ObjectMapper(){}
}
