package be.yellowduck.sports.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RouteRepositoryTests @Autowired constructor(
    val routeRepository: RouteRepository
) {

    @Test
    fun findExistingSingleRoute() {
        val route: Route = routeRepository.findById(101795286).orElseThrow()
        assertThat(route.name).isEqualTo("ritje rond brugge 103 km")
        assertThat(route.polyline).isEqualTo("st`wHefqS??BcAA{@D{BAOzB?AQ?_AJYPQt@w@H@F@RBNFL@@SMg@CIeB_GcCgIi@qBi@sBoAgF]wAS{@c@gBYsAgB_HiDkNmLue@OcAWw@eDoMsAsFYWmNmk@EUoEsQkFiTiBuHe@gBOs@eAoCC_B@Y@WBiB?SAK?a@AWCGKa@DKBKDQDUDe@Dc@TwBPyBFu@H{@d@uCNa@p@qAZm@~BoGTi@Lg@Jo@He@@G@GNgAHo@PwARwATgBN_Ap@oDV{Af@cCP_A@IHc@Fa@w@CG?uIq@cGuAm@U_C_AmD_BmB{@w@]kEqBUKiAi@EAUK[O[MyFiCyDeB_Ac@iGqC??DWl@kDFUFWd@mAdAq@`@oA^wANgAb@{DEy@[y@Ai@x@qERmCFiD`@mCBGHK`EoADEXQzE}EVmAnAaLAUgAJc@QuAyBg@i@sHwGqAgAi@e@wAqAqD_DYU{NqQEO?OJaAF[gJgCuMuDUQE[d@qF`@yEdA_MH{@hAwMHw@tA_On@sGp@uGz@gIHsA?k@BwF@yEBiFJeNBkJ@yCDqN?s@Gk@MKOHqBzAuAj@aFtBgAb@q@VaEj@sGz@gIx@oBa@MGO]I?oJrCeANo@Gq@UuLeE_Be@UDC@g@r@}GrIw@bA}AbBkOtPoDzDyAbBoAvAwB~BiAzAsAjBoAzB_@T[?wCoLsCeOcAiEG]{Ao@kEcDeFa@qCw@_BOiHu@oBUqFFsDw@wGyCoDBUIq@YeKaH{Ak@o@?WTyBrEm@r@sC|BoC~@sBjB}CnEsBeBe@~CaBdMcAvHa@hAkD|Em@vAk@lDu@dEmHwDiBu@uFRu@Mk@a@s@w@k@}@w@}@mAq@e@K_BNu@ZyHhDeEjBKDsAx@cClBOx@Gv@WAYCc@Y{@qCOQiBi@oAsAcBy@_BaBgAq@Yg@gDcHyCaICO@MbA}@FSJmDm@iLKwBJmCk@qFmAoGDsC_BoHKcA?mDAwA}TeIa@Ou@gCi@eBaAqC_BeDiHuE}BmBj@qHbAeNHo@tAwKtBeO{AmAjCcShAsIxAeJdCgNNYHC@_@v@yFE}@c@gAYa@kDpFg@p@{@hAILeBhDw^t}@eFdMK\\GIw@gAGGDkCYmAeC{IsDeNkAyDkYw^Cg@v@yN@s@ec@}JaRaE_Dq@yEyAiBiAwBiAgGsDgCaAKQEYoAwTKH{@v@IJG{@qA{ToBu`@Q}BkDqKIQIBEXi@dCCFKF{@Cw@k@}TgWu@_A{@}AqFqLIVc@fAgAl@mB|CwEdOq@jIQfAoCnGKNMK_G}Js@iBu@mA{@{@cMmIQ@k@n@w@fBi@zG[jA[`@W@Ot@y@bJi@bDYNmAe@mAKQXS|AWZ]LCN?f@t@|Gd@xBBl@EhAABANCVc@rDi@~Ae@bCSbBK`F_@bAkAz@u@nEGV{@x@KNo@lJQvA[dCQv@mAnCi@|@SjA]pEYbAQfBX`EChHkAnD[~@oCfC_@z@{AxL_ArEOpAMhEd@jHqAdIEnGDXkK|HoDVsANi@Dc@FBx@DvBHpDFtANdIHdDDBHNV`@l@NxC}@xDOd@PP\\Et^Z`JDjAJ~FPpHDb@^fFBrCSpLi@xHPrJVbN@x@@vAeBvDAbFa@pGWFo@Qq@NIJIp@?^FdAHPG\\CRERJtC\\pCTzG@|AI`@QCc@jAiAjBQh@OrBg@fGAPWjCC\\ALOnBGxA@XLbJDp@NlDPnCJhB{@p@uBtB}@|@s@t@e@~@Up@GP]dAu@lBs@xBSx@HHFVHp@Hz@Hp@HzC?`@ATClDEfAEf@@jCBb@\\nF@PBb@H|BH@r@RVh@BX?f@?VEXw@nFDzBDfABf@@h@Br@x@rRDpABr@JlCB`@Bj@@b@Qp@sBn@k@dAStAAl@^zEBhBAf@MJGJSb@}Px]Ub@g@bAcKrSwBrEUd@}@hBwAvCOd@Qj@a@xBsCfQIf@eChOW~BQfDWvGCt@IdBe@lLWrGYzBm@xB[l@GHDVMDkBQe@HYXk@xAa@`A?????d@YzBFf@Rb@pApAcAxDaErOe@bD{@nI_AjIGA{i@pPuT~G??_IdCKhC[jDw@jFqBfH{AjDmXjh@}BnD}B~DkBjD_AlBuAhD_BrCXBNInBeDbAeCb@k@JAFFd@|AZ|BnK`v@`@lCl@nCtBnFz@zBn@`BhV~m@HTrL`[hFfNpOla@fAzCj@~ALh@FXpA`Ev@tBy@nAe@t@QT}A~@K^w@dKNbIG`@ULgAAM?g@hAiAlD?FBHz@fAF`@sCdMIh@E\\_ArQc@|BQd@YXSL?n@EjBOtAEXJ\\zAdETTfAPNR|@vDz@zAt@^hBFrCr@pA~@z@jAHPh@xA`@bAJTnBrDL\\x@|B~B|GzCvFtCpErClCtAlBVVRl@xBvIpC`HrArBtCxFjAxDlDpE~C~Az@zADPDPXzA?|DV`B|@lBlB`An@lAr@vBRbALt@Nx@Lp@`A~CRnATxA@R?LRJhArBP^rBtDnG`NNRTZbExEvA`DdA~A@L??CL{QvSsB~BUXDHDLjBvFBHNTlDzB~@|@v@`AzBpCpJdK`GfHDDPTjCbDLNhAvA^b@xA|A`ChC`FpFnEvEbAfABThCrC_@x@[j@eE|HiBfDgCtE_AdBwAjCkBlDcClEeFjJ_ErHwBzD_EpHiHvM??q@lAwEtIyDfH_B`Dm@|BMv@Kv@Eh@IrAAf@Ab@JxCPxBp@~InAfPFt@dAvG~AnIf@hCp@hDfDnQf@dCh@rCvBhJ?RAX_@fECjAE|COh@IHP^h@|ArAnEb@`AX`@R^nAjARJRLr@ZdBt@TJ\\LLBZ@?H?DN|A@TCTW|D?^?@@\\D\\lAdGt@vDt@rDHb@h@lCzAjHZzAL~@@N?PI^Ob@K\\ADGPVVZZj@~@Pn@HPF\\D\\JzAF|ADt@@V@\\?TKbBAV?^?vAh@jJ@DPnB\\rEDl@BT@X`@lG????a@mGAYTGnGaA`@IBBDNZjE?lDMvFDvBj@jD|@zBVXnBbCTV~@lAbDlC`@TIL}JwFSGO@kEv@aAAsB\\NlB\\~CNXNDBAJGb@~BL|@H`AZzDFv@JfAHl@VzAFVDTVbAhAkAhAaAnCkAoCjAiA`AiAjAWcAEUGWW{ACQBPVzAFVDTVbALb@b@jAh@bAzA|B~@lBrAbFr@tDBNL|@Ff@Db@@Z@TAfCUrCCZCh@?hA@lBb@fDrFrR@DBFgEtCqWbR{KdJgBxAcAz@iEpDURBRDVl@dCh@xBHZ\\hBj@jB^zAb@tB\\`BHXBNTx@Rn@HZjF~IPZzDrGb@`ALf@~@xDJd@DXTrAPv@jAvCTjCTnAtAjEVz@XbAdCxHL`@FPBHX~@rA|EPl@HVTz@Lb@`@xAH^\\pBDZFExCoBfAc@|@]NGRKdAi@`@Q^KpAMv@s@PULUfCcEdCyFXSdCo@PIHNdEdLbBlCxBvEhBhHHf@HPbClBRJDLhBhFRf@BDFLdAzALXDJLjAd@rI^nLGh@[n@El@hBlIRb@NDvF}BtGwFJ[DmAJm@b@uA^_@tNeDlIsD~BmBbFmGFGzC}ExJsHrBeA|D_@^Hn@qCf@}DVuCPmBp@sGJw@FBJHB@DDrAhAjFbITt@z@jFtB|EhDzHVdBLx@DZP~@ZdAJVHDb@XPb@F\\BFDTBF\\bBH^\\|F@NHb@rA~ERxAF|UPd@jA^n@z@\\t@Pf@Rn@~AxEn@tF^tBAPEH]Po@DKJOzAP~E\\hCdAhEf@jD`@`DPbBL|C]rLSt@s@v@Cx@|JbYIp@gAhBIf@j@zCF~Kl@vD`@fHDRtAy@nCcBdG_HvGgDtBqC~B}AVIvA_@`CSxDBpHaC~CKjBaA~B]\\[`AgBz@e@Xa@^{Cl@yBDYPKdDK~B[bDBbEq@tJgEvDkCl@O`COjBeAR@HFDHxBlP`@rCRjA`@vBL`@Hd@Hd@JELCn@MLCFEFKHg@^mBJk@|@kFzBgIx@yAZY\\Up@Wz@E`It@`A^NDVNRNFc@K{C}@uJi@eNzEaI`IeJbB}A~AcAPQzEwIjAaDxIyQ|@sAFSjGiB`@YHK~A}BTWrCaCn@g@`H}FVUjEoDLKdA{@pBcBl@g@LIFGFJD@@@NGBGFB@?JDPHFFf@d@NNj@i@HSNM|B{ApIgIh@g@`BwAdCqBdBkATOTQ^WTK\\MtCaAhFaBz@]fDkBPMvBcBjCaBrBm@lBiAvD{AhDuBh@[bGwBlCeAtAi@lAg@|C{A`@Sz@c@t@[rAsAPMXj@x@vAPl@FXJv@dBhEDJ~@~Ad@x@Xl@BTALCZHD`A`@DHHLZiCrDc[tAyMr@gGpJuMP_@hCuFHQHHVT`BxA??~@z@xDjDzBpBt@p@|@x@DITa@NHFKjCwED_@CGLUjC}EhE_I@WCKs@}@GWCQ?MBUDQHU~BeDpK}MhA{AxC_Ez@}@jAg@p@ItERjFc@TQnAbEbFeCxBy@zDcAlCgBrExC`@BfDu@lE{@pDg@pCcAn@OPElCw@jFuDPSRSn@gALY@A@Cl@kAr@e@xBW\\CfD_@~JoA|AW\\E~AQnD_@tJgABMAKCOeA{B{@gBm@iBcBcHQk@e@wAy@{Cg@kDKu@OcAk@}F[eDnAP~Dl@RB~AVbJtAbBVNBvF~@^FXDx@LB@`C`@n@JzARXBH@jORrCHJ@rE?rA_@x@e@v@s@`C{Bh@e@~Ao@xB?~@Jd@Bd@AlBKh@E~BMdAgOD_AlAiVJi@Zm@R]t@sA`@yAx@mDlAsCfA{D|CiIjAqCCc@a@qBy@qB?IBObFmL^}BjDkKl@e@|@k@dBoA]iBOoAIk@M{B?cJImBEo@]gCoAqIBKKgAEg@\\O`Ag@\\SZOLGrAk@h@Sq@aA[cASe@cA{B]w@GKuBeDaBiBAI?IReAeCgDk@w@kR{P{AuAjIeMl@_LB[\\{Gj@uHTeCrBwS?g@iBI?QcHeQ[{@aBoE_BqEISgAyCSi@gB_FwF{OkAyB_DyFgCkEcCgGeBcGkEmPeBuDa@kAMu@WcFgAiEo@wBAK[u@CIQ_@GI}@C_@_@eBuDs@eCgBkEeCiDCAi@s@qBcC}AcAq@UiAb@]?k@Os@o@EIEIcBcHwDaLgBuJGi@E_@KgBFiBjAiIF}@Sy@Qc@KSa@Y{CiBmD}BqCmA_@EM??KGa@c@qBg@_BeCwFoAkDo@}EAGq@qGEa@y@cHSgBoAwGk@eBa@m@]WyDc@wAkAMWWo@K[a@aBY{AYgBO_AWqAc@{@eCmBKI@Gt@eHn@yGLoDF_BBkAV}Jt@{Lt@{G`AyLVmQJqCE_DR_FVgn@Bu@PkF?MBo@GkE?[Gg@l@wHzAsIn@qC|@gDz@oC????h@}AJSl@aAVi@l@kAj@cAX]|HcLf@s@??z@qAtD_Fl@i@^]Ni@jBoB|@w@X@HI`@_@f@a@DIv@y@b@qCHeCH_@dA}Fn@{BZeAVg@|@sBnDkFvCcDDEdAmBrC_DjBgDvE{JLOJULUFO@A`AcCf@_ALUd@eGLsAZoCQSAUMo@EOKe@{A}Dy@yBqAiCs@{As@gB{CmIyAqGG}@AKIkBAmBDsAFcDFyCF{C@[DwBD_CImZBY~A?dBAH?@_@fBK|BOEyAC{A??")
    }

    @Test
    fun findNonExistingSingleRoute() {
        val route: Route? = routeRepository.findByIdOrNull(-1)
        assertThat(route).isNull()
    }

    @Test
    fun findAllByOrderByChangedAtDesc() {
        val routes: List<Route> = routeRepository.findAllByOrderByChangedAtDesc().toList()
        assertThat(routes.size).isEqualTo(375)
        assertThat(routes.first().name).isEqualTo("Pajottenland Oetingen")
    }

    @Test
    fun findAllBySportOrderByChangedAtDesc() {
        val routes: List<Route> = routeRepository.findAllBySportOrderByChangedAtDesc("hike").toList()
        assertThat(routes.size).isEqualTo(20)
        assertThat(routes.first().name).isEqualTo("2020-01-05")
    }

    @Test
    fun findAllBySportOrderByChangedAtDescNonExistingSport() {
        val routes: List<Route> = routeRepository.findAllBySportOrderByChangedAtDesc("unknown").toList()
        assertThat(routes.size).isEqualTo(0)
    }

    @Test
    fun findSportsWithCount() {
        val result: List<Summary> = routeRepository.findSportsWithCount().toList()
        assertThat(result.size).isEqualTo(6)
        assertThat(result.get(0)).isEqualTo(Summary(name = "hike", count = 20L))
        assertThat(result.get(1)).isEqualTo(Summary(name = "jogging", count = 12))
        assertThat(result.get(2)).isEqualTo(Summary(name = "mtb", count = 26))
        assertThat(result.get(3)).isEqualTo(Summary(name = "mtb_easy", count = 78))
        assertThat(result.get(4)).isEqualTo(Summary(name = "racebike", count = 227))
        assertThat(result.get(5)).isEqualTo(Summary(name = "touringbicycle", count = 12))
    }

    @Test
    fun findTotalRouteCountExisting() {
        val totalCount: Long = routeRepository.findTotalRouteCount(33896215)
        assertThat(totalCount).isEqualTo(375)
    }

    @Test
    fun findTotalRouteCountNonExisting() {
        val totalCount: Long = routeRepository.findTotalRouteCount(-1)
        assertThat(totalCount).isEqualTo(0)
    }

}
