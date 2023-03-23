package pers.zymir.lucky.draw.serivce.draw;

import pers.zymir.lucky.draw.model.req.LotteryDrawingReq;
import pers.zymir.lucky.draw.model.res.LotteryDrawingRes;

public interface ILotteryDrawingService {

  LotteryDrawingRes drawing(LotteryDrawingReq req);

}
