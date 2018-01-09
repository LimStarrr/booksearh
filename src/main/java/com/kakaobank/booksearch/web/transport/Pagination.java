package com.kakaobank.booksearch.web.transport;

import com.kakaobank.booksearch.web.transport.request.SortType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@EqualsAndHashCode
@ToString
public class Pagination {
	private Long lastId = Long.MAX_VALUE;

	private Integer size = Integer.MAX_VALUE;

	private String sortType = SortType.CATEGORY.name();

	public PageRequest toPageRequest() {
		return new PageRequest(0, this.size, new Sort(Direction.DESC, this.sortType));
	}
}