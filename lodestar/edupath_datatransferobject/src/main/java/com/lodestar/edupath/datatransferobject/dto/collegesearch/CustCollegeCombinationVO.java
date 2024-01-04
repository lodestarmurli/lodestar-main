package com.lodestar.edupath.datatransferobject.dto.collegesearch;

import java.io.Serializable;

public class CustCollegeCombinationVO implements Serializable
{

	private static final long	serialVersionUID	= 1L;

	private int					streamId;
	private int					combinationId;
	private String				electiveCombAvailable;
	private double				annualFee;
	private int					cutOff;

	public String getElectiveCombAvailable()
	{
		return electiveCombAvailable;
	}

	public void setElectiveCombAvailable(String electiveCombAvailable)
	{
		this.electiveCombAvailable = electiveCombAvailable;
	}

	public double getAnnualFee()
	{
		return annualFee;
	}

	public void setAnnualFee(double annualFee)
	{
		this.annualFee = annualFee;
	}

	public int getStreamId()
	{
		return streamId;
	}

	public void setStreamId(int streamId)
	{
		this.streamId = streamId;
	}

	public int getCombinationId()
	{
		return combinationId;
	}

	public void setCombinationId(int combinationId)
	{
		this.combinationId = combinationId;
	}

	public int getCutOff()
	{
		return cutOff;
	}

	public void setCutOff(int cutOff)
	{
		this.cutOff = cutOff;
	}

	@Override
	public String toString()
	{
		return "CustCollegeCombinationDTO [streamId=" + streamId + ", combinationId=" + combinationId + ", electiveCombAvailable=" + electiveCombAvailable
				+ ", annualFee=" + annualFee + ", cutOff=" + cutOff + "]";
	}
}
