package com.tenniscourts.tenniscourts;


public interface TennisCourtMapper {
    TennisCourtDTO map(TennisCourt source);

    TennisCourt map(TennisCourtDTO source);
    
    TennisCourtDTO mapWithSchedule(TennisCourt tennisCourt);
}
