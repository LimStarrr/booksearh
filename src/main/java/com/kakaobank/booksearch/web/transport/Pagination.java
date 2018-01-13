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
	private Integer lastId = Integer.MAX_VALUE;

	private Integer size = Integer.MAX_VALUE;

	private SortType sortType = SortType.CATEGORY;

	public PageRequest toPageRequest() {
		return new PageRequest(0, this.size, new Sort(Direction.DESC, this.sortType.name()));
	}

	public PageRequest toPageOffsetRequest() {
		return new PageRequest(lastId, this.size, new Sort(Direction.DESC, this.sortType.name()));
	}
}