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

	private int sortType = SortType.TITLE.getType();

	public PageRequest toPageRequest() {
		return new PageRequest(0, this.size, new Sort(Direction.DESC, "id"));
	}

	public PageRequest toPageOffsetRequest() {
		return new PageRequest(lastId, this.size, new Sort(Direction.DESC, sortType(getSortType())));
	}

	private String sortType(int sortType) {
		if(sortType == SortType.TITLE.getType())
			return SortType.TITLE.getName();
		if(sortType == SortType.DATETIME.getType())
			return SortType.DATETIME.getName();
		if(sortType == SortType.PRICE.getType())
			return SortType.PRICE.getName();

		return SortType.TITLE.getName();
	}
}