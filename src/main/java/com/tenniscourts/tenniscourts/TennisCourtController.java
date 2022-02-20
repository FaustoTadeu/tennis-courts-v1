package com.tenniscourts.tenniscourts;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tennis-court")
@AllArgsConstructor
@Api(tags= {"Tennis Court API"})
public class TennisCourtController extends BaseRestController {

    private final TennisCourtService tennisCourtService;

    @PostMapping
    @ApiOperation(value = "Create new Tennis Court.", notes = "Create new Tennis Court.")
    public ResponseEntity<Void> addTennisCourt(@ApiParam(required = true, name = "TennisCourtDTO") TennisCourtDTO tennisCourtDTO) {
        return ResponseEntity.created(locationByEntity(tennisCourtService.addTennisCourt(tennisCourtDTO).getId())).build();
    }

    @GetMapping("/{tennisCourtId}")
    @ApiOperation(value = "Find Tennis Court by id.", notes = "Find Tennis Court by id.")
    public ResponseEntity<TennisCourtDTO> findTennisCourtById(@ApiParam(required = true, value = "Id of tennis court to be found.") @PathVariable("tennisCourtId") Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtById(tennisCourtId));
    }

    @GetMapping("/schedules/{tennisCourtId}")
    @ApiOperation(value = "Find Tennis Court and all schedules by id.", notes = "Find Tennis Court and all schedules by id.")
    public ResponseEntity<TennisCourtDTO> findTennisCourtWithSchedulesById(@ApiParam(required = true, value = "Id of tennis court to be found.") @PathVariable("tennisCourtId") Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtWithSchedulesById(tennisCourtId));
    }
}
