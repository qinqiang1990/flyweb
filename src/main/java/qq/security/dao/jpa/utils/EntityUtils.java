package qq.security.dao.jpa.utils;

import java.lang.reflect.*;

public class EntityUtils {
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getEntityClass(Class<T> entity, int index) {
		if (null == entity)
			return null;
		if (Object.class.equals(entity))
			return (Class<T>) Object.class;

		ParameterizedType type = (ParameterizedType) entity
				.getGenericSuperclass();
		if (null == type)
			return (Class<T>) Object.class;
		Type[] types = type.getActualTypeArguments();
		if (null == types || types.length == 0)
			return (Class<T>) Object.class;
		if (index > -1) {
			return (Class<T>) (index < types.length ? types[index]
					: types[types.length - 1]);
		} else {
			return (Class<T>) types[0];
		}
	}

	public static <T> Class<T> getEntityClass(Class<T> entity) {
		return (Class<T>) getEntityClass(entity, 0);
	}
}