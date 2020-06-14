package com.server.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ClientInfoResponse extends BaseResponse {
	private List<ClientInfo> lst;
	private ClientInfo obj;
	
	
}
